# Dagger CI Configuration

This directory contains the Dagger CI configuration for the small-fast-router project. The project uses the Dagger CLI for running CI pipelines.

## Structure

```
ci/
├── dagger.json           # Dagger configuration file
├── main.py              # Main CI pipeline
├── common/               # Shared utilities and configurations
│   └── utils.py          # Common utility functions
└── pipelines/            # Root-level specific pipelines
    └── release.py        # Release pipeline

utilities/
└── ci/                   # Module-specific CI
    └── module.py         # Module-specific pipeline
```

## Usage

To run the main CI pipeline:

```bash
cd ci
dagger run python main.py
```

To run a module-specific pipeline:

```bash
cd utilities/ci
dagger run python module.py
```

To run a specific pipeline from the pipelines directory:

```bash
cd ci/pipelines
dagger run python release.py
```

## Adding a New Module

When adding a new module to the project:

1. Create a `ci` directory in the module
2. Add a `module.py` file with the module-specific pipeline
3. Import common utilities from the root CI folder

Example:

```python
import sys
import dagger
import os

# Add the root ci directory to the Python path
sys.path.append(os.path.abspath(os.path.join(os.path.dirname(__file__), '..', '..')))
from ci.common.utils import create_gradle_container, build_module, test_module

# New module CI pipeline
async def new_module_pipeline():
    # Initialize Dagger client
    async with dagger.Connection() as client:
        # Get reference to the source code
        source = client.host().directory(".")

        # Create a container for building the project
        builder = create_gradle_container(client, source)

        # Module-specific build steps
        # ...

        print("Pipeline completed!")

if __name__ == "__main__":
    import asyncio
    asyncio.run(new_module_pipeline())
```

## Prerequisites

Before running the Dagger pipelines, make sure you have the Dagger CLI installed. You can install it following the instructions at [dagger.io/install](https://dagger.io/install).

For Windows, you can use:

```bash
choco install dagger
```

Or with PowerShell:

```powershell
winget install dagger.dagger
```

Verify your installation with:

```bash
dagger version
```
