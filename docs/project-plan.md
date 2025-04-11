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
- `utilities/`: A separate module (currently empty)

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
- Route matching implementation
- Trie structure for efficient route storage
- Parameter extraction
- Integration of SIMD operations with route matching
- Complete HTTP server integration

## Implementation Plan

### Phase 1: Complete Route Matching Implementation

#### Tasks
1. **Implement Trie Structure**
   - Create a trie data structure for efficient route storage
   - Implement node merging for optimization
   - Add support for handling optional segments

2. **Implement Parameter Extraction**
   - Add logic to extract parameters from matched routes
   - Implement named parameter support
   - Handle optional parameters

3. **Complete the `get` Method in HttpRouter2**
   - Implement route matching algorithm
   - Add support for route prioritization
   - Handle edge cases (trailing slashes, empty segments)

#### Deliverables
- Functional route matching implementation
- Unit tests for route matching
- Documentation for route matching algorithm

### Phase 2: SIMD Integration

#### Tasks
1. **Apply Vector API Concepts**
   - Integrate SIMD operations from HttpRouter.java into HttpRouter2.java
   - Implement vector processing strategy using stack/queue for vectors
   - Add support for vector masks

2. **Optimize Matching Algorithm**
   - Implement 3-case enum switch for node types
   - Optimize for different vector sizes
   - Add support for early termination

3. **Implement Memory Optimization**
   - Add direct memory access to String's underlying byte array
   - Minimize memory allocations during matching
   - Optimize for cache locality

#### Deliverables
- SIMD-optimized route matching implementation
- Performance tests comparing SIMD vs. non-SIMD implementations
- Documentation for SIMD optimization techniques

### Phase 3: HTTP Server Integration

#### Tasks
1. **Integrate Router with Vert.x**
   - Connect HttpRouter2 with Vert.x HTTP server
   - Add support for different HTTP methods
   - Implement request handling with route matching

2. **Add Middleware Support**
   - Implement middleware chain
   - Add support for pre/post processing
   - Implement error handling

3. **Enhance API**
   - Add fluent API for route definition
   - Implement content negotiation
   - Add support for route groups

#### Deliverables
- Complete HTTP server integration
- Example application using the router
- Documentation for HTTP server integration

### Phase 4: Performance Optimization and Benchmarking

#### Tasks
1. **Create Benchmarks**
   - Implement benchmark suite
   - Add support for different route patterns
   - Measure throughput and latency

2. **Compare with Existing Routers**
   - Benchmark against popular Java routers
   - Analyze performance differences
   - Identify optimization opportunities

3. **Optimize Based on Results**
   - Implement identified optimizations
   - Re-run benchmarks to verify improvements
   - Document optimization techniques

#### Deliverables
- Benchmark suite
- Performance comparison report
- Optimized router implementation

## Timeline

### Phase 1: Complete Route Matching Implementation
- Duration: 3 weeks
- Dependencies: None

### Phase 2: SIMD Integration
- Duration: 4 weeks
- Dependencies: Phase 1

### Phase 3: HTTP Server Integration
- Duration: 2 weeks
- Dependencies: Phase 1

### Phase 4: Performance Optimization and Benchmarking
- Duration: 3 weeks
- Dependencies: Phase 2, Phase 3

## Risks and Mitigation

### Technical Risks

1. **Java Vector API Stability**
   - Risk: The Vector API is still in the incubator module and may change
   - Mitigation: Monitor JDK updates and adapt code as needed

2. **Performance Bottlenecks**
   - Risk: SIMD operations may not provide expected performance gains
   - Mitigation: Implement fallback non-SIMD code paths and benchmark extensively

3. **Complex Route Patterns**
   - Risk: Some complex route patterns may be difficult to match efficiently
   - Mitigation: Identify edge cases early and design algorithms to handle them

### Project Risks

1. **Scope Creep**
   - Risk: Adding too many features may delay completion
   - Mitigation: Prioritize core functionality and defer non-essential features

2. **Integration Challenges**
   - Risk: Integration with Vert.x may be more complex than anticipated
   - Mitigation: Start with simple integration and incrementally add features

## Conclusion

The small-fast-router project has a solid foundation with its ANTLR4-based route parsing and initial SIMD proof of concept. By following this implementation plan, the project can be completed with all required components to create a high-performance HTTP router that leverages SIMD operations for efficient route matching.
