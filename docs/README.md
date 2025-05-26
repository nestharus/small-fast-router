ff# Small-Fast-Router Documentation

This directory contains the documentation for the small-fast-router project.

## Documentation

### [Technical Design Document](technical-design.md)
The consolidated technical design document covering:
- The 13 compiler phases
- How `**` is handled via forward/backward split
- Matching algorithm
- Handler scoring
- Implementation status

### [Route DSL Specification](route-dsl-specification.md)
Complete specification of the route pattern syntax based on the ANTLR4 grammar:
- Syntax elements (static text, wildcards, captures, etc.)
- Valid combinations and restrictions
- Examples of valid and invalid patterns

## Important Note

The route grammar is defined by the ANTLR4 `.g4` files in `router-compiler/src/main/resources/grammar/`. Any syntax not in those files does not exist.
