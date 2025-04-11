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

### 2. SIMD Optimization Components

#### 2.1 Vector API Integration
- Uses Java's Vector API (incubator module) for SIMD operations
- **ByteVector** for byte array comparisons
- **VectorMask** for handling partial loads
- **VectorSpecies<Byte>.SPECIES_PREFERRED** for optimal performance

#### 2.2 Memory Access Optimization
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
- SIMD-based route matching implementation
- Vector-optimized trie structure for efficient route storage
- Parameter extraction during SIMD matching
- HTTP server integration with the SIMD-optimized router
- Benchmarking and performance optimization

## Implementation Plan

### Phase 1: SIMD-Based Core Implementation

#### Tasks
1. **Implement SIMD Foundation**
   - Set up Vector API integration for byte array operations
   - Implement direct memory access to String's underlying byte array via VarHandles
   - Create vector processing strategy using stack/queue for vectors
   - Implement vector masks and back bytemasks for partial chunks

2. **Develop SIMD-Optimized Node Structure**
   - Create base node structure designed around vector size constraints
   - Implement the 3-case enum switch for node types (StaticNode, StarNode, DoubleStarNode)
   - Design node structure to handle vector-sized chunks
   - Implement automatic splitting of longer static text at vector boundaries

3. **Build Basic Trie with SIMD Matching**
   - Create a trie data structure optimized for SIMD operations
   - Implement static node matching using SIMD comparisons
   - Add support for node merging within vector size constraints
   - Implement memory optimization techniques for cache locality

#### Deliverables
- SIMD-optimized core router structure
- Basic route matching for static routes
- Unit tests for SIMD operations and static route matching
- Documentation for SIMD implementation approach

### Phase 2: Advanced Routing Features

#### Tasks
1. **Implement Wildcard Matching**
   - Add support for single-segment wildcards (StarNode)
   - Implement prefix and suffix handling for wildcards
   - Add path wildcard support (DoubleStarNode)
   - Implement reversed sub-trie processing for path wildcards as a key component of the matching algorithm

2. **Implement Parameter Extraction**
   - Add logic to extract parameters during the matching process
   - Implement named parameter support
   - Handle optional parameters
   - Optimize parameter storage for minimal allocations

3. **Complete Route Selection Logic**
   - Implement route prioritization rules
   - Add support for multiple handlers per route
   - Handle edge cases (trailing slashes, empty segments, etc.)

#### Deliverables
- Complete SIMD-based route matching implementation
- Support for all route pattern types described in the design
- Parameter extraction functionality
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

3. **Optimize Based on Results**
   - Implement identified optimizations
   - Re-run benchmarks to verify improvements
   - Document optimization techniques
   - Ensure consistent behavior across platforms with different vector sizes

#### Deliverables
- Benchmark suite
- Performance comparison report
- Optimized router implementation
- Documentation of optimization techniques

## Timeline

### Phase 1: SIMD-Based Core Implementation
- Duration: 4 weeks
- Dependencies: None

### Phase 2: Advanced Routing Features
- Duration: 4 weeks
- Dependencies: Phase 1

### Phase 3: HTTP Server Integration
- Duration: 3 weeks
- Dependencies: Phase 2

### Phase 4: Performance Optimization and Benchmarking
- Duration: 3 weeks
- Dependencies: Phase 3

## Risks and Mitigation

### Technical Risks

1. **Java Vector API Stability**
   - Risk: The Vector API is still in the incubator module and may change
   - Mitigation: Monitor JDK updates and adapt code as needed
   - Mitigation: Design the code to isolate Vector API calls for easier updates

2. **Performance Bottlenecks**
   - Risk: SIMD operations may not provide expected performance gains in all scenarios
   - Mitigation: Implement fallback non-SIMD code paths for comparison
   - Mitigation: Benchmark extensively with different route patterns and payload sizes

3. **Complex Route Patterns**
   - Risk: Some complex route patterns may be difficult to match efficiently with SIMD
   - Mitigation: Identify edge cases early and design specialized algorithms
   - Mitigation: Consider hybrid approaches for particularly complex patterns

### Project Risks

1. **Scope Creep**
   - Risk: Adding too many features may delay completion
   - Mitigation: Prioritize core functionality and defer non-essential features

2. **Integration Challenges**
   - Risk: Integration with Vert.x may be more complex than anticipated
   - Mitigation: Start with simple integration and incrementally add features

## Conclusion

The small-fast-router project aims to create a high-performance HTTP router by leveraging SIMD operations as a fundamental part of its design. By implementing SIMD operations from the beginning and building the router architecture around vector processing, this project will deliver exceptional performance for route matching. The implementation plan ensures that SIMD optimization is not an afterthought but a core aspect of the router's design, aligning with the vision outlined in the design document.
