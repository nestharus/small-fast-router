# Small-Fast-Router Project Plan

## Project Overview

The small-fast-router project aims to create a high-performance HTTP router that leverages SIMD (Single Instruction, Multiple Data) operations for efficient route matching. The router is designed to handle complex URL patterns with wildcards and parameter extraction while maintaining exceptional performance.

## Key Components

### 1. Core Router Components

#### 1.1 Route Parsing System
- **ANTLR4 Grammar Files**:
  - `RouteLexer.g4`: Defines tokens for route patterns
  - `RouteParser.g4`: Defines grammar rules for parsing route patterns
  - Generated parser code in `src/main/gen/org/nestharus/router/`

#### 1.2 Route Node Types
- **StaticNode**: For static text segments (with optional support)
- **StarNode**: For single-segment wildcards (with prefix/suffix support)
- **DoubleStarNode**: For path wildcards (with minimum segment count)

#### 1.3 Router Implementation
- **HttpRouter.java**: Initial proof of concept for SIMD operations
- **HttpRouter2.java**: More structured implementation using ANTLR4 grammar
  - Currently parses routes but doesn't implement matching logic
  - Needs to be extended with a trie structure for efficient route storage

#### 1.4 Parameter Handling
- **HttpParameters.java**: Interface for handling extracted parameters
  - Provides methods to get and set parameters
  - Parameters are extracted during route matching

### 2. Matching Strategy Components

#### 2.1 SIMD Optimization
- Uses Java's Vector API (incubator module) for SIMD operations
- **ByteVector** for byte array comparisons
- **VectorMask** for handling partial loads
- **VectorSpecies<Byte>.SPECIES_PREFERRED** for optimal performance
- Vector processing optimizations for efficient matching

#### 2.2 Byte Comparison Optimization
- Direct byte-by-byte comparison for certain node types
- Efficient for short nodes or nodes with few children
- Avoids SIMD overhead in cases where it would be inefficient

#### 2.3 Adaptive Strategy Selection
- Runtime selection between SIMD and byte comparison methods
- Selection based on node characteristics (length, number of children)
- Per-list-of-nodes optimization for maximum performance

#### 2.4 Memory Access Optimization
- Direct memory access to String's underlying byte array via VarHandles
- Avoids unnecessary string allocations during matching
- Uses `STRING_VALUE_HANDLE` and `STRING_CODER_HANDLE` for direct access

### 3. HTTP Server Integration

#### 3.1 Vert.x Integration
- **Server.java**: Proof of concept for HTTP server integration
- Uses Vert.x for HTTP server functionality
- Currently only matches a hardcoded URL ("/5000")
- Needs to be integrated with the router implementation

### 4. Build and Project Structure

#### 4.1 Gradle Build System
- Java 23 with Vector API support
- Lombok for reducing boilerplate
- Spotless for code formatting
- ANTLR4 for grammar parsing

#### 4.2 Project Structure
- `src/main/java/org/nestharus/router/`: Main source code
- `src/main/resources/org/nestharus/router/`: ANTLR4 grammar files
- `src/main/gen/org/nestharus/router/`: Generated ANTLR4 parser code
- `src/test/java/org/nestharus/router/`: Test code

#### 4.3 Dependencies
- ANTLR Runtime for grammar parsing
- Vert.x for HTTP server
- Netty for network operations
- SLF4J for logging
- JUnit for testing

### 5. Testing Components

#### 5.1 Test Classes
- **HttpRouter2Test.java**: Tests various route pattern types
  - Static routes
  - Star routes (single-segment wildcards)
  - Double-star routes (path wildcards)
  - Mixed patterns
  - Invalid routes
- **TestRouteParser.java**: Tests the ANTLR4 parser directly

## Current Status

### Implemented Features
- Route parsing grammar using ANTLR4
- Basic node types for different route segments
- Proof of concept for SIMD operations
- Basic HTTP server integration with Vert.x
- Comprehensive test suite for route parsing

### Missing Components
- Adaptive matching strategy implementation (SIMD and byte comparison)
- Vector-optimized trie structure for efficient route storage
- Compile-time optimization and evaluation process
- Trie serialization and deserialization mechanisms
- Parameter extraction during matching
- HTTP server integration with the optimized router
- Benchmarking and performance optimization

## Implementation Plan

### Phase 1: Core Matching Implementation

#### Tasks
1. **Implement Matching Foundations**
   - Set up Vector API integration for SIMD operations
   - Implement direct memory access to String's underlying byte array via VarHandles
   - Create vector processing strategy using stack for trie traversal
   - Implement vector masks and back bytemasks for partial chunks
   - Develop byte comparison implementation for alternative matching strategy

2. **Develop Node Structure Variants**
   - Create base node structure with common functionality
   - Implement register-sized prefix nodes for SIMD matching
   - Implement compressed prefix nodes for byte comparison
   - Implement the 3-case enum switch for node types (StaticNode, StarNode, DoubleStarNode)
   - Design node structure to handle both matching strategies

3. **Build Adaptive Trie Structure**
   - Create a trie data structure supporting both matching strategies
   - Implement static node matching using both SIMD and byte comparison
   - Add support for node merging within vector size constraints
   - Implement memory optimization techniques for cache locality
   - Develop strategy selection logic based on node characteristics

#### Deliverables
- Adaptive core router structure supporting both matching strategies
- Basic route matching for static routes
- Unit tests for both SIMD and byte comparison operations
- Documentation for implementation approach

### Phase 2: Advanced Routing Features

#### Tasks
1. **Implement Wildcard Matching**
   - Add support for single-segment wildcards (StarNode) with both matching strategies
   - Implement prefix and suffix handling for wildcards
   - Add path wildcard support (DoubleStarNode) with the reversed sub-trie approach
   - Implement segment boundary handling for wildcard matching

2. **Implement Parameter Extraction**
   - Add logic to extract parameters during the matching process
   - Implement named parameter support
   - Handle optional parameters
   - Optimize parameter storage for minimal allocations

3. **Complete Route Selection Logic**
   - Implement route prioritization rules
   - Add support for multiple handlers per route
   - Handle edge cases (trailing slashes, empty segments, etc.)

4. **Implement Compile-time Evaluation Process**
   - Develop evaluation criteria for matching strategy selection
   - Create benchmarking mechanism for node list performance comparison
   - Implement strategy selection based on evaluation results
   - Design serialization format for optimized tries

#### Deliverables
- Complete adaptive route matching implementation
- Support for all route pattern types described in the design
- Parameter extraction functionality
- Compile-time evaluation mechanism
- Unit tests for complex route patterns

### Phase 3: HTTP Server Integration

#### Tasks
1. **Integrate Router with Vert.x**
   - Connect the SIMD-optimized router with Vert.x HTTP server
   - Implement HTTP method-based routing (GET, POST, PUT, DELETE, etc.)
   - Implement request handling with route matching
   - Integrate Netty's ByteBuf for efficient buffer management

2. **Implement Multiple Handler Support**
   - Add support for multiple handlers per route pattern
   - Implement handler chaining in registration order
   - Implement error handling for route matching

3. **Enhance API**
   - Add fluent API for route definition
   - Create developer-friendly error messages for route syntax issues
   - Implement comprehensive route validation

#### Deliverables
- Complete HTTP server integration with HTTP method-based routing
- Example application using the router
- Documentation for HTTP server integration
- Implementation of multiple handler support

### Phase 4: Performance Optimization and Benchmarking

#### Tasks
1. **Create Benchmarks**
   - Implement benchmark suite
   - Add support for different route patterns
   - Measure throughput and latency
   - Test performance with various vector sizes

2. **Compare with Existing Routers**
   - Benchmark against popular Java routers
   - Analyze performance differences
   - Identify optimization opportunities

3. **Implement Trie Serialization and Deserialization**
   - Create serialization format for optimized tries
   - Implement serialization logic for different vector species sizes
   - Develop runtime deserialization mechanism
   - Add platform detection for appropriate trie selection

4. **Optimize Based on Results**
   - Implement identified optimizations
   - Re-run benchmarks to verify improvements
   - Document optimization techniques
   - Ensure consistent behavior across platforms with different vector sizes

#### Deliverables
- Benchmark suite
- Performance comparison report
- Trie serialization and deserialization implementation
- Optimized router implementation
- Documentation of optimization techniques

## Timeline

### Phase 1: Core Matching Implementation
- Duration: 5 weeks
- Dependencies: None

### Phase 2: Advanced Routing Features
- Duration: 5 weeks
- Dependencies: Phase 1

### Phase 3: HTTP Server Integration
- Duration: 3 weeks
- Dependencies: Phase 2

### Phase 4: Performance Optimization and Benchmarking
- Duration: 4 weeks
- Dependencies: Phase 3

## Risks and Mitigation

### Technical Risks

1. **Java Vector API Stability**
   - Risk: The Vector API is still in the incubator module and may change
   - Mitigation: Monitor JDK updates and adapt code as needed
   - Mitigation: Design the code to isolate Vector API calls for easier updates

2. **Performance Bottlenecks**
   - Risk: SIMD operations may not provide expected performance gains in all scenarios
   - Mitigation: Implement byte comparison as an alternative strategy
   - Mitigation: Use compile-time evaluation to select the optimal strategy
   - Mitigation: Benchmark extensively with different route patterns and payload sizes

3. **Complex Route Patterns**
   - Risk: Some complex route patterns may be difficult to match efficiently
   - Mitigation: Identify edge cases early and design specialized algorithms
   - Mitigation: Use the adaptive strategy to select the best approach for each pattern

4. **Serialization Compatibility**
   - Risk: Serialized tries may not be compatible across different JVM versions
   - Mitigation: Implement version checking in the serialization format
   - Mitigation: Add fallback to runtime trie generation if deserialization fails

### Project Risks

1. **Scope Creep**
   - Risk: Adding too many features may delay completion
   - Mitigation: Prioritize core functionality and defer non-essential features

2. **Integration Challenges**
   - Risk: Integration with Vert.x may be more complex than anticipated
   - Mitigation: Start with simple integration and incrementally add features

## Conclusion

The small-fast-router project aims to create a high-performance HTTP router by leveraging both SIMD operations and optimized byte comparison methods as fundamental parts of its design. By implementing an adaptive strategy that selects the optimal matching approach based on node characteristics, this project will deliver exceptional performance for route matching across a wide range of patterns and platforms.

The implementation plan ensures that performance optimization is not an afterthought but a core aspect of the router's design, with compile-time evaluation and serialization enabling the best possible runtime performance. This approach aligns with the vision outlined in the design document, creating a router that outperforms existing solutions through innovative techniques and careful optimization.
