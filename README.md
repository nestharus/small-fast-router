# Small-Fast-Router

A high-performance HTTP router that leverages SIMD (Single Instruction, Multiple Data) operations for efficient route matching. This project aims to create one of the fastest Java HTTP routers available, with support for complex URL patterns, wildcards, and parameter extraction.

## Project Overview

Small-Fast-Router is designed to provide:

- **Exceptional Performance**: Uses Java's Vector API for SIMD operations to process multiple bytes in parallel
- **Flexible Route Patterns**: Supports static segments, wildcards, path wildcards, and named parameters
- **Clean API**: Type-safe parameter handling with an intuitive route definition syntax
- **Memory Efficiency**: Minimizes allocations during route matching with direct memory access

## Current Status

This project is currently in the proof-of-concept stage. The route parsing grammar is implemented and working, but the actual route matching logic using SIMD operations is still under development.

The project implements a compiler that transforms route patterns into DFAs/DAWGs. See the technical design document for details.

## Prerequisites

- Java 24 or higher (required for Vector API support)
- Gradle 8.11.1 or higher

## Getting Started

### Clone the Repository

```bash
git clone https://github.com/nestharus/small-fast-router.git
cd small-fast-router
```

### Build the Project

```bash
./gradlew build
```

### Rebuild Grammar
```bash
./gradlew :router-compiler:compileJava --no-build-cache
```

### Run Tests

```bash
./gradlew test
```

## Project Structure

- `src/main/java/org/nestharus/router/`: Main source code
  - `HttpRouter.java`: Initial SIMD proof of concept
  - `HttpRouter2.java`: Grammar-based implementation
  - `Server.java`: Vert.x HTTP server integration
- `src/main/resources/org/nestharus/router/`: ANTLR4 grammar files
  - `RouteLexer.g4`: Lexer grammar for route patterns
  - `RouteParser.g4`: Parser grammar for route patterns
- `src/main/gen/org/nestharus/router/`: Generated ANTLR4 parser code
- `src/test/java/org/nestharus/router/`: Test code
- `docs/`: Project documentation
- `utilities/`: A separate module (currently empty)

## Documentation

See [Technical Design Document](docs/technical-design.md) for the complete compiler pipeline and implementation status.

## Features

### Route Pattern Support

The route grammar is defined by the ANTLR4 `.g4` files in `router-compiler/src/main/resources/grammar/`. 
See the test files for examples of supported syntax.

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## License

This project is licensed under the [MIT License](LICENSE).