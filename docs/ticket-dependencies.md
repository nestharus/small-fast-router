# Small-Fast-Router Ticket Dependencies

This document provides a visual representation of the dependencies between implementation tickets for the small-fast-router project.

## Critical Path

Based on the dependency graph, the critical path for implementation is:

```
SFRT-006 → SFRT-010 → SFRT-001 → SFRT-002 → SFRT-003 → SFRT-007/008/009 → SFRT-004 → SFRT-005 → SFRT-011 → SFRT-013 → SFRT-014 → SFRT-015 → SFRT-016/017 → SFRT-018 → SFRT-020
```

## Implementation Phases

### Phase 1: SIMD-Based Core Implementation (4 weeks)
- **SFRT-006**: Implement Vector API Integration
  - *This is the starting point as all node types and matching algorithms depend on SIMD operations*
  - Establishes the core SIMD capabilities that the entire router depends on
  - Implements direct memory access to String's byte array
  - Creates utility classes for vector operations
- **SFRT-010**: Implement Vector Processing Strategy (depends on SFRT-006)
  - *Defines the overall strategy for using SIMD operations in route matching*
  - Implements vector stack/queue for processing URL segments
  - Optimizes for common cases using SIMD operations
  - Establishes memory access patterns for optimal performance

- **SFRT-001**: Implement Node Types for Route Segments (depends on SFRT-006)
  - *Node types must be designed with SIMD operations in mind from the start*
  - Implements StaticNode, StarNode, and DoubleStarNode with SIMD-compatible data structures
  - Stores static text as byte arrays for direct SIMD comparison
  - Designs node structure to handle vector-sized chunks
  - Implements automatic splitting of longer static text at vector boundaries
- **SFRT-002**: Implement ANTLR4-Based Route Parser (depends on SFRT-001)
  - Parses route patterns into SIMD-compatible node structures

### Phase 2: Advanced Routing Features (4 weeks)
- **SFRT-003**: Implement Trie Structure for Route Storage (depends on SFRT-001, SFRT-002)
  - *Trie structure must be optimized for SIMD operations*
  - Implements node merging up to vector size limit
  - Uses byte arrays for direct SIMD comparison
  - Optimizes for cache locality and memory efficiency
- **SFRT-007**: Implement SIMD-Based Static Node Matching (depends on SFRT-001, SFRT-006, SFRT-010)
  - Optimizes static node matching using SIMD operations
  - Implements early termination for non-matching segments
  - Optimizes for common case of single vector size
- **SFRT-008**: Implement SIMD-Based Wildcard Node Matching (depends on SFRT-001, SFRT-006, SFRT-010)
  - Optimizes wildcard node matching using SIMD operations
  - Implements prefix and suffix handling for wildcards
  - Optimizes parameter extraction using vector operations
- **SFRT-009**: Implement SIMD-Based Path Wildcard Matching (depends on SFRT-001, SFRT-006, SFRT-010)
  - Optimizes path wildcard matching using SIMD operations
  - Implements reversed sub-trie processing for path wildcards
  - Optimizes for minimum segment count checking

- **SFRT-004**: Implement Route Matching Algorithm (depends on SFRT-003, SFRT-007, SFRT-008, SFRT-009)
  - *Integrates all SIMD-based node matching into a complete algorithm*
  - Implements route selection priority
  - Uses SIMD operations for all matching steps
  - Implements special handling for path wildcards with reversed sub-trie processing
- **SFRT-005**: Implement Parameter Extraction and Type Conversion (depends on SFRT-004)
  - Extracts parameters using SIMD operations
  - Implements type conversion utilities for parameters
  - Handles default values and required parameters

### Phase 3: HTTP Server Integration (3 weeks)
- **SFRT-011**: Implement HTTP Router Interface (depends on SFRT-004, SFRT-005, SFRT-010)
  - Creates a clean API that leverages the SIMD-optimized matching
  - Implements handler registration and lookup
  - Supports different HTTP methods
- **SFRT-012**: Implement Vert.x Integration (depends on SFRT-011)
  - Integrates with Vert.x for HTTP server functionality
  - Converts Vert.x request to router request
  - Handles Vert.x-specific features
- **SFRT-013**: Implement Middleware Support (depends on SFRT-011)
  - Adds middleware support to the router
  - Implements middleware chain execution
  - Supports global and route-specific middleware
- **SFRT-014**: Implement Fluent API (depends on SFRT-011, SFRT-013)
  - Creates a fluent API for route definition
  - Implements route groups and content negotiation
  - Supports path parameters and handler registration

### Phase 4: Performance Optimization and Benchmarking (3 weeks)
- **SFRT-015**: Implement Benchmark Suite (depends on SFRT-012, SFRT-014)
  - Creates benchmarks to measure router performance
  - Implements comparison with existing routers
  - Generates performance reports
- **SFRT-016**: Optimize Memory Usage (depends on SFRT-015)
  - Analyzes memory usage patterns
  - Reduces unnecessary allocations
  - Optimizes node structure memory layout
- **SFRT-017**: Optimize CPU Cache Usage (depends on SFRT-015)
  - Analyzes cache miss patterns
  - Optimizes memory access patterns
  - Implements cache-friendly data structures
- **SFRT-018**: Implement Final Performance Tuning (depends on SFRT-015, SFRT-016, SFRT-017)
  - Identifies performance bottlenecks
  - Optimizes critical paths
  - Implements specialized handling for common cases

### Additional Features
- **SFRT-019**: Implement Compile-Time Route Validation (depends on SFRT-002)
  - Implements a route pattern validator
  - Adds compile-time checks using annotation processing
  - Creates error reporting for invalid patterns
  - Can be developed in parallel with Phase 2 tickets

### Documentation and Examples
- **SFRT-020**: Create Comprehensive Documentation and Examples (depends on SFRT-014, SFRT-018, SFRT-019)
  - Creates API documentation with usage examples
  - Implements example applications
  - Creates performance guide and troubleshooting guide

## Parallel Development Opportunities

The dependency graph reveals several opportunities for parallel development:

1. After implementing SFRT-006 (Vector API Integration):
   - **SFRT-010** (Vector Processing Strategy) can be started immediately.

2. After implementing SFRT-001 and SFRT-002:
   - **SFRT-003** (Trie Structure) and **SFRT-019** (Compile-Time Validation) can be developed in parallel.

3. After implementing SFRT-001, SFRT-006, and SFRT-010:
   - **SFRT-007**, **SFRT-008**, and **SFRT-009** (SIMD-Based Matching) can be developed in parallel.

4. After implementing SFRT-011:
   - **SFRT-012** (Vert.x Integration) and **SFRT-013** (Middleware Support) can be developed in parallel.

5. After implementing SFRT-015:
   - **SFRT-016** (Memory Optimization) and **SFRT-017** (CPU Cache Optimization) can be developed in parallel.

## Recommended Implementation Sequence

Based on the corrected dependency graph, the recommended implementation sequence is:

1. **SFRT-006: Implement Vector API Integration** - This establishes the SIMD foundation that the entire project depends on.

2. **SFRT-010: Implement Vector Processing Strategy** - This defines the overall approach for using SIMD operations in route matching.

3. **SFRT-001: Implement Node Types for Route Segments** - This creates the fundamental node types designed for SIMD operations.

4. **SFRT-002: Implement ANTLR4-Based Route Parser** - This parses route patterns into SIMD-compatible node structures.

Starting with these tickets will allow the team to validate the SIMD approach early and establish the foundation for all other aspects of the router. The node types, trie structure, and matching algorithms all depend on SIMD operations, so these must be implemented first.

## Risk Management

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
