# Current Status and Proof of Concept Implementation

This document outlines the current state of the small-fast-router project, including implemented features, proof of concept code, and next steps.

## Project Overview

The small-fast-router project aims to create a high-performance HTTP router that leverages SIMD (Single Instruction, Multiple Data) operations for efficient route matching. The project is currently in the proof-of-concept stage with several implementations being explored.

## Current Implementations

### 1. HttpRouter.java (Proof of Concept)

This file contains initial experiments with Java's Vector API for SIMD operations:

- Demonstrates basic ByteVector operations for string comparison
- Contains conceptual notes about route matching strategies
- Explores the use of vector masks for partial string matching
- Includes comments about wildcard handling strategies

Key concepts being explored:
- Using SIMD for fast string comparison
- Handling wildcards with vector operations
- Strategies for matching route segments

Current limitations:
- Not a complete implementation
- Primarily focused on SIMD proof of concept
- No actual route matching functionality

### 2. HttpRouter2.java (Grammar-Based Implementation)

This is a more structured implementation that uses ANTLR4 for parsing route patterns:

- Defines a grammar for route patterns (RouteLexer.g4 and RouteParser.g4)
- Implements node types for different route segments:
  - StaticNode: For static text segments (with optional support)
  - StarNode: For single-segment wildcards
  - DoubleStarNode: For path wildcards
- Parses route patterns into a list of nodes
- Has a skeleton implementation of the `get` method for route matching

Recent updates:
- Modified grammar to support OPTIONAL on static text
- Added tests for routes with OPTIONAL support on static text
- Created a prototype that parses a single route to a "list" of nodes
- Added optimization for wildcard nodes by combining suffix with additional node data

Current capabilities:
- Successfully parses complex route patterns
- Handles static segments, wildcards, and path wildcards
- Supports named parameters and optional segments
- Comprehensive test suite for route parsing

Current limitations:
- Routes need to be exploded into a tree to handle optionals
- The actual route matching logic is not implemented yet
- No trie structure for efficient route storage
- Parameter extraction is not implemented
- Current prototype doesn't handle merging of static text or conversion to SIMD vectors

### 3. Server.java (Integration Proof of Concept)

This file demonstrates integration with Vert.x for HTTP server functionality:

- Sets up a basic HTTP server
- Contains experimental code for URL matching using SIMD
- Includes comments about parameter handling strategies
- Demonstrates direct memory access to String's underlying byte array

Current capabilities:
- Basic HTTP server setup
- Experimental SIMD-based URL matching
- Direct memory access via VarHandles

Current limitations:
- Very basic implementation
- Only matches a hardcoded URL ("/5000")
- No integration with the router implementation

## Test Coverage

The project has good test coverage for the route parsing functionality:

- `HttpRouter2Test.java`: Tests various route pattern types
  - Static routes
  - Star routes (single-segment wildcards)
  - Double-star routes (path wildcards)
  - Mixed patterns
  - Invalid routes
- `TestRouteParser.java`: Tests the ANTLR4 parser directly

## Project Structure

The project is set up as a Gradle project with the following structure:

- `src/main/java/org/nestharus/router/`: Main source code
- `src/main/resources/org/nestharus/router/`: ANTLR4 grammar files
- `src/main/gen/org/nestharus/router/`: Generated ANTLR4 parser code
- `src/test/java/org/nestharus/router/`: Test code
- `utilities/`: A separate module (currently empty)

The build system uses Gradle with several plugins:
- Java 23 with Vector API support
- Lombok for reducing boilerplate
- Spotless for code formatting
- ANTLR4 for grammar parsing

## Next Steps

Based on the current state of the project, the following next steps are recommended:

1. **Complete the route matching implementation**:
   - Implement the `get` method in `HttpRouter2.java`
   - Create a trie structure for efficient route storage
   - Implement parameter extraction
   - Explode routes into a tree to handle optionals

2. **Integrate SIMD operations**:
   - Apply the Vector API concepts from `HttpRouter.java` to `HttpRouter2.java`
   - Implement the vector processing strategy using stack/queue for vectors
   - Optimize the matching algorithm for SIMD operations
   - Implement the 3-case enum switch for node types

3. **Complete the HTTP server integration**:
   - Integrate the router with the Vert.x HTTP server
   - Implement request handling with route matching
   - Add support for different HTTP methods

4. **Add performance benchmarks**:
   - Create benchmarks to measure router performance
   - Compare with existing router implementations
   - Optimize based on benchmark results

5. **Enhance the API**:
   - Add fluent API for route definition
   - Implement middleware support
   - Add content negotiation

## Implementation Notes

### Vector Processing Strategy

The current design for vector processing includes:

- Scanning forward along URI string, converting into vectors
- Keeping N vectors on a stack, popping them as they're consumed
- Clearing stack on match (set index to 0)
- When hitting a wildcard, jumping to string index + node byte length in URI string and clearing stack
- Using bitwise operations and stack length to grab more bytes from URI

### Optimization Techniques

- Node types implemented as a 3-case enum switch statement (optimized to jump table by Temurin)
- Default case handles nodes with length > 3 vectors (>192 bytes) - rare in practice
- Most nodes will likely be 1 vector in size
- Route selection prioritizes static segments over wildcards, with sorting by type and length

## Conclusion

The small-fast-router project shows promising progress with solid foundations:

- A well-defined grammar for route patterns
- Proof of concept for SIMD-based string matching
- Integration with a modern HTTP server framework
- Recent updates to support OPTIONAL on static text
- Clear optimization strategy for vector processing

The next phase should focus on completing the route matching implementation and integrating the SIMD operations for optimal performance.
