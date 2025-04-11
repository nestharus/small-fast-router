# Utilities CI Module

This directory contains the CI pipeline for the utilities module of the small-fast-router project. The project uses the Dagger CLI for running CI pipelines.

## Usage

To run the utilities module CI pipeline:

```bash
cd utilities/ci
dagger run python module.py
```

## Structure

```
utilities/ci/
├── __init__.py          # Makes this directory a Python package
├── module.py            # Main CI pipeline for the utilities module
├── dagger.json          # Dagger configuration file
├── Pipfile              # Python dependencies
└── Pipfile.lock         # Locked Python dependencies
```

## Dependencies

This module depends on the root CI module for common utilities and configurations.

## Prerequisites

Before running the Dagger pipelines, make sure you have the Dagger CLI installed. See the main CI README for installation instructions.
