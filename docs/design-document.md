# SIMD-Optimized HTTP Router Design Document

## Overview

This document outlines the design of a high-performance HTTP router that leverages SIMD (Single Instruction, Multiple Data) operations for efficient route matching. The router is designed to handle complex URL patterns with wildcards and parameter extraction while maintaining exceptional performance.

## 1. Core Design Principles

### 1.1 Performance-First Approach
- Utilizes Java Vector API for SIMD operations to process multiple bytes in parallel
- Minimizes memory allocations during route matching
- Direct memory access to avoid unnecessary string operations
- Optimized for modern CPU architectures with vector processing capabilities

### 1.2 Flexible Route Patterns
- Support for static segments, wildcards, and path wildcards
- Named parameter extraction
- Optional segments
- Prefix and suffix patterns within segments

### 1.3 Clean API
- Type-safe parameter handling
- Intuitive route definition syntax
- Minimal runtime overhead

## 2. Route Grammar & Capabilities

### 2.1 Supported Pattern Types

#### Static Segments
- Basic text: `/users`
- Optional segments: `/users?`

#### Single-segment Wildcards
- Basic: `*`
- Named: `*{varName}`
- Optional: `*?`
- Named + Optional: `*{varName}?`
- Within-segment patterns:
  - Prefix: `abc*`
  - Suffix: `*xyz`
  - Both: `abc*xyz`
  - Optional parts: `abc?*`, `*xyz?`, `abc?*xyz?`

#### Path Wildcards
- Basic: `**`
- Named: `**{varName}`
- With parameter lists: `**[*{name1}, *{name2}?, *, *?]`
  - Parameters can be optional (marked with `?`)
  - Non-optional parameters define minimum segment count

### 2.2 Example Routes

```
/api/users/*{id}/profile
/api/**/settings
/api/v1?/users/**[*{type}, *{id}?, *]/data   // Minimum 2 segments
/api/**[*{org}?, *{repo}, *{file}]/raw       // Minimum 2 segments
/*.txt
/files?/*{filename}.txt
files*.txt              // Wildcard with suffix in segment
abc*xyz/def            // Wildcard with prefix and suffix
abc?*xyz?/def         // Optional prefix and suffix
```

## 3. Technical Implementation

### 3.1 Vector API & Memory Access

The router uses Java's Vector API (currently in the incubator module) to perform SIMD operations:

- `ByteVector` for byte array comparisons
- `VectorMask` for handling partial loads
- `VectorSpecies<Byte>.SPECIES_PREFERRED` for optimal performance on the target CPU

Direct memory access is achieved through:
- VarHandles to access String's underlying byte array without copying
- Netty's ByteBuf for efficient buffer management

### 3.2 Node Structure

All route nodes are fundamentally static nodes with specialized behavior for wildcards:

#### Base Node Structure
- Contains merged static text up to vector size
- Uses back bytemask to handle:
  - Final chunk shorter than vector size
  - Early termination at wildcard marker
- Matched using SIMD from front
- Static nodes have no vector mask

#### Node Types

1. **StaticNode**
   - Represents a static text segment
   - Can be marked as optional

2. **StarNode (Wildcard)**
   - Represents a single-segment wildcard
   - Can have prefix and/or suffix
   - Can be optional
   - Captures parameter between prefix and suffix
   - Can combine suffix with additional node data for optimization

3. **DoubleStarNode (Path Wildcard)**
   - Represents a multi-segment wildcard
   - Can have a minimum segment count
   - Switches to reversed sub-trie processing after matching

Node types are implemented as a 3-case enum switch statement, which Temurin JVM optimizes into a jump table for efficient dispatch.

### 3.3 Matching Process

#### Static Node Matching
1. Load vector-sized chunks from the URL
2. Apply back bytemask for partial chunks
3. Compare with stored vectors using SIMD operations
4. Advance by matched length

#### Wildcard Node Processing
When a node ending in `*` is matched:
1. Find the end of the current segment (next slash)
2. Extract parameter value between node end and segment end
3. Continue matching with next node

#### Path Wildcard Processing
When a node ending in `**` is matched:
1. Switch to reversed sub-trie processing
2. Jump to end of URL
3. Match against character-reversed sub-trie
4. Extract parameter value between node end and reversed match start

### 3.4 Parameter Extraction

Parameters are extracted during the matching process:
- After `*` match: Text between node end and matched suffix
- After `**` match: Text between node end and reversed match
- Exact byte offsets from matching process

Parameters are stored in an array and can be accessed by index or name (if named).

## 4. Route Selection Priority

When multiple routes match a URL, the router selects the most specific route:

1. Static segments are prioritized over wildcards
2. Longer static segments are prioritized over shorter ones
3. Single-segment wildcards (`*`) are prioritized over path wildcards (`**`)

For example, with URL `/a/b/c/d` and routes:
- `/a/b/*/*`
- `/a/*/c/d`

The first route is selected because in the second segment, `b` (static) is selected over `*` (wildcard).

This prioritization is implemented by sorting children by type (static, wildcard, glob) on the first dimension and by length (descending) on the second dimension. This ensures that the most specific route is always selected during the matching process.

## 5. Parser Implementation

The router uses ANTLR4 for parsing route patterns:

1. **RouteLexer**: Tokenizes the route pattern
2. **RouteParser**: Parses the tokens into a syntax tree
3. **RouteParserBaseVisitor**: Visits the syntax tree to build route nodes

This approach provides:
- Clear separation of concerns
- Robust error handling
- Maintainable grammar definition

## 6. Performance Considerations

### 6.1 Vector Size Constraints
- All nodes are built around the CPU's preferred vector species size
- This size determines the maximum merge size for static text
- Text merging stops at vector size limit

### 6.2 Memory Access Patterns
- Minimizes cache misses through careful memory layout
- Avoids unnecessary string allocations
- Uses direct memory access where possible

### 6.3 Algorithmic Optimizations
- Early termination of non-matching routes
- Efficient trie structure for route storage
- Specialized handling for common cases
- Vector processing optimizations:
  - Scan forward along URI string, converting into vectors
  - Keep vectors in a stack/queue structure, clearing on match
  - For nodes longer than 3 vectors (>192 bytes), use a loop comparison (rare case)
  - Most nodes will likely be 1 vector in size
  - When hitting a wildcard, jump to string index + node byte length in URI string and clear stack

## 7. Future Enhancements

- Compile-time route validation
- More advanced pattern matching capabilities
- HTTP method-based routing
- Content negotiation
- Middleware support
- Automatic documentation generation
