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
- **Important Limitation**: Only one path wildcard (`**`) is allowed per route pattern
  - This constraint is essential for the efficient reversed sub-trie processing algorithm
  - Multiple path wildcards would require expensive backtracking or brute-force approaches to determine which segments belong to each wildcard
  - While theoretically possible to implement, supporting multiple path wildcards would significantly degrade performance

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
- Matched using either SIMD or byte comparisons based on node characteristics
- Static nodes have no vector mask
- Longer static text is automatically split into multiple nodes at vector boundaries
- This splitting is purely an implementation detail and doesn't affect the logical trie structure

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

#### Node Structure Variants

Nodes can be structured in two different ways depending on the matching strategy:

1. **Register-sized Prefix Nodes (SIMD Matching)**
   - All nodes are of the same length up to the vector size
   - Nodes join by common prefix with no duplicates
   - Optimized for SIMD operations
   - Used when nodes are long and have more than 1 child or many children

2. **Compressed Prefix Nodes (Byte Matching)**
   - Nodes are maximally compressed to common prefixes
   - More memory-efficient representation
   - Used when nodes are short or have few children
   - Avoids SIMD overhead in cases where byte comparisons are faster

### 3.3 Adaptive Matching Strategy

The router dynamically selects between SIMD and byte comparison methods based on node characteristics:

- **SIMD Matching**: Used when nodes are long and have more than 1 child or many children
- **Byte Comparisons**: Used when nodes are short or have few children

This selection is made per list of nodes (a node's children), not globally. Byte comparisons can be faster than SIMD in specific cases by avoiding copy overhead.

Additionally, the matching strategy considers node characteristics and pattern complexity:

- **SIMD Matching**: Optimal for longer nodes and nodes with many children
- **Byte Comparisons**: Better for shorter nodes and nodes with few children

This hybrid approach leverages SIMD's performance while using byte comparison's efficiency where appropriate.

### 3.4 Matching Process

#### Static Node Matching with SIMD
1. Load vector-sized chunks from the URL
2. Apply back bytemask for partial chunks
3. Compare with stored vectors using SIMD operations
4. Advance by matched length

#### Static Node Matching with Byte Comparisons
1. Access the URL's byte array directly
2. Compare bytes sequentially
3. Advance by matched length

#### Wildcard Node Processing with SIMD
When a node ending in `*` is matched using SIMD:
1. Find the end of the current segment (next slash)
2. The wildcard match is limited to the current segment (as in all implementations)
3. The back of the vector is padded with 1s to handle partial vectors
4. Vector alignment must be maintained for efficient SIMD operations
5. Extract parameter value between node end and segment end
6. Continue matching with next node

#### Wildcard Node Processing with Byte Comparison
When a node ending in `*` is matched using byte comparison:
1. Find the end of the current segment (next slash)
2. The wildcard match is limited to the current segment (as in all implementations)
3. The algorithm can track exact positions byte-by-byte without vector alignment concerns
4. Extract parameter value between node end and segment end
5. Continue matching with next node

#### Path Wildcard Processing
When a node ending in `**` is matched:
1. Switch to reversed sub-trie processing
2. Jump to end of URL
3. Match against character-reversed sub-trie
4. Extract parameter value between node end and reversed match start

Both SIMD and byte comparison approaches use this reversed sub-trie strategy for path wildcards, though the actual matching mechanics differ based on the selected approach. The reversed approach eliminates the need for backtracking and allows for efficient matching of variable-length path segments.

The design allows only one path wildcard per route pattern. This limitation is necessary for the reversed sub-trie approach to work efficiently. With multiple path wildcards, the algorithm would need to try different combinations of segment allocations between wildcards, essentially requiring a brute-force approach that would negate the performance benefits of the reversed sub-trie strategy. While theoretically possible to implement, the performance cost of supporting multiple path wildcards would be prohibitive for a high-performance router.

### 3.5 Parameter Extraction

Parameters are extracted during the matching process:
- After `*` match: Text between node end and matched suffix
- After `**` match: Text between node end and reversed match
- Exact byte offsets from matching process

Parameters are stored in an array and can be accessed by index or name (if named).

## 4. Route Selection Priority

### 4.1 Priority Rules

When multiple routes match a URL, the router selects the most specific route:

1. Static segments are prioritized over wildcards
2. Longer static segments are prioritized over shorter ones
3. Single-segment wildcards (`*`) are prioritized over path wildcards (`**`)

For example, with URL `/a/b/c/d` and routes:
- `/a/b/*/*`
- `/a/*/c/d`

The first route is selected because in the second segment, `b` (static) is selected over `*` (wildcard).

This prioritization is implemented by sorting children by type (static, wildcard, glob) on the first dimension and by length (descending) on the second dimension. This ensures that the most specific route is always selected during the matching process.

### 4.2 Multiple Handlers

The router supports multiple handlers for the same route pattern. When a route pattern is registered multiple times with different handlers, all handlers are chained and executed in registration order when the route is matched.

This allows for flexible middleware-like patterns where multiple handlers can process the same request, similar to how other routers like Vert.x handle multiple handlers for the same route.

## 5. Parser Implementation

### 5.1 ANTLR4 Grammar

The router uses ANTLR4 for parsing route patterns:

1. **RouteLexer**: Tokenizes the route pattern into tokens like SLASH, WILDCARD, STATIC_TEXT, etc.
2. **RouteParser**: Parses the tokens into a syntax tree according to the grammar rules
3. **RouteParserBaseVisitor**: Visits the syntax tree to build route nodes

This approach provides:
- Clear separation of concerns
- Robust error handling
- Maintainable grammar definition

### 5.2 Error Handling

The parser provides detailed error messages for malformed routes:

1. **Syntax Error Detection**: The ANTLR4 parser automatically detects syntax errors during parsing
2. **Error Reporting**: Errors are reported with line and character position information
3. **Exception Handling**: When adding routes, syntax errors are converted to `IllegalArgumentException` with descriptive messages

Common syntax errors include:
- Unbalanced brackets or braces: `/path/{varname`
- Invalid variable group syntax: `/path/**[*{foo}, *{bar},]`
- Multiple path wildcards: `**/hi/**`
- Invalid optional markers: `/hello??`

Developers receive immediate feedback during route registration, with errors that pinpoint the exact location and nature of syntax issues.

## 6. Performance Considerations

### 6.1 Vector Size Constraints
- All nodes are built around the CPU's preferred vector species size
- This size determines the maximum merge size for static text
- Text merging stops at vector size limit
- Longer static segments are automatically split into multiple nodes at vector boundaries
- This splitting is transparent to the matching algorithm, which processes each node the same way

### 6.2 Memory Access Patterns
- Minimizes cache misses through careful memory layout
- Avoids unnecessary string allocations
- Uses direct memory access where possible

### 6.3 Algorithmic Optimizations
- Early termination of non-matching routes
- Efficient trie structure for route storage
- Specialized handling for common cases
- Compile-time optimization strategy:
  - The trie is optimized for byte or SIMD comparison ahead of time per list of nodes
  - Both SIMD and byte comparison versions are created and evaluated during compilation
  - The faster version is chosen for each list of nodes
  - Optimizations are done during compile time by creating a trie for each vector species (64, 128, 256, 512)
  - Optimized tries are serialized to binary files for later deserialization at runtime
- Vector processing optimizations:
  - Vectors are pushed onto a stack as the trie is traversed
  - When a node fails to match, pop from the stack and backtrack
  - When a node matches, continue traversing down the trie
  - For nodes longer than 3 vectors (>192 bytes), use a loop comparison (rare case)
  - Most nodes will likely be 1 vector in size
  - When hitting a wildcard, jump to string index + node byte length in URI string and continue traversal
  - Vector alignment must be maintained for efficient SIMD operations
  - The vector approach requires careful handling of node boundaries
- Byte comparison optimizations:
  - Direct access to byte arrays without copying
  - Efficient for short nodes or nodes with few children
  - Avoids SIMD overhead in cases where it would be inefficient
  - Provides simpler implementation without vector alignment concerns
  - Can be more efficient for certain node types and pattern structures
  - Used when the overhead of SIMD operations would outweigh their benefits

### 6.4 Cross-Platform Consistency
- The router maintains the same trie structure and matching algorithm across all platforms
- Only the node boundaries vary based on the platform's preferred vector size
- At startup, a trie is selected for the specific vector species of the platform
- Tries for different vector species sizes (64, 128, 256, 512) are created and serialized during compilation
- Only one trie is deserialized and loaded into memory at runtime, based on the platform's capabilities
- This approach ensures consistent behavior while optimizing for each platform's specific capabilities
- Performance will vary across platforms with different vector sizes, but will consistently outperform traditional routers

## 7. Compile-time Evaluation Process

A key aspect of the router's performance optimization is the compile-time evaluation process that determines the most efficient matching strategy for each list of nodes:

### 7.1 Evaluation Criteria

- **Node Length**: Longer nodes generally benefit more from SIMD operations
- **Number of Children**: Lists with many children typically perform better with SIMD
- **Overhead Analysis**: The evaluation weighs the overhead of SIMD operations against the speed of direct byte comparisons

### 7.2 Evaluation Process

1. During compilation, both SIMD and byte comparison versions of the trie are created
2. Each list of nodes is evaluated with both matching strategies
3. Performance metrics are collected for each approach
4. The faster version is selected for each list of nodes
5. The final optimized trie configurations are serialized to binary files for each vector species size (64, 128, 256, 512)
6. These serialized tries contain all the necessary information about which matching strategy to use for each node list

### 7.3 Trie Serialization and Deserialization

1. After the evaluation process, each optimized trie is serialized to a binary file
2. The serialization format preserves:
   - The trie structure
   - Node types and their properties
   - The selected matching strategy (SIMD or byte comparison) for each list of nodes
   - All metadata required for efficient matching
3. Serialized tries are included as resources in the final application package
4. This approach separates the compilation-time optimization from runtime execution

### 7.4 Runtime Selection

1. At startup, the router detects the platform's vector capabilities
2. The appropriate serialized trie is deserialized from its binary file based on the platform's vector species
3. Only one trie is loaded into memory at runtime
4. The deserialized trie already contains all the optimal matching strategy decisions for each list of nodes
5. This approach eliminates runtime decision-making overhead while still providing platform-specific optimizations

## 8. Core Features

### 8.1 Compile-time Route Validation

The router provides compile-time validation of routes to catch errors early in the development cycle. This is a core feature of the project.

Validation includes:
- Syntax validation through the grammar rules
- Semantic validation for specific constraints (e.g., no multiple path wildcards in the same route)
- Structural validation of variable groups and parameter lists

Developers receive immediate feedback with descriptive error messages for invalid patterns during compilation.

### 8.2 HTTP Method-Based Routing

The router supports HTTP method-based routing as a core feature. This allows routes to be defined with specific HTTP methods (GET, POST, PUT, DELETE, etc.) and ensures that requests are only matched against routes that support the requested method.
