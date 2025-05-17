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

See the [current status document](docs/current-status.md) for more details on implemented features and next steps.

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

Detailed documentation is available in the `docs` directory:

- [Design Document](docs/design-document.md): Technical architecture and design principles
- [Current Status](docs/current-status.md): Current state of the project and next steps
- [Project Plan](docs/project-plan.md): Implementation strategy and timeline

## Features

### Route Pattern Support

Small-Fast-Router supports a rich set of route patterns:

- **Static segments**: `/users`, `/api/v1`
- **Optional segments**: `/users?`, `/api/v1?`
- **Single-segment wildcards**: `*`, `*{id}`, `files*.txt`
- **Path wildcards**: `**`, `**{path}`, `**[*{type}, *{id}]`

Example routes:

```
/api/users/*{id}/profile
/api/**/settings
/api/v1?/users/**[*{type}, *{id}?, *]/data
/files?/*{filename}.txt
```

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## License

This project is licensed under the [MIT License](LICENSE).