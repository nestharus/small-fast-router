#!/usr/bin/env python
"""
Test script to verify that the utilities/ci module is properly set up.
"""

import os
import sys
import subprocess

def print_separator():
    print("\n" + "-" * 50 + "\n")

# Print environment information
print("Environment Information:")
print(f"Python executable: {sys.executable}")
print(f"Python version: {sys.version}")
print(f"Current directory: {os.getcwd()}")
print(f"PYTHONPATH: {os.environ.get('PYTHONPATH', 'Not set')}")

print_separator()

# Add the current directory to the Python path
sys.path.append(os.path.abspath(os.path.dirname(__file__)))

# Try to import from the utilities.ci module
print("Testing imports from utilities/ci:")
try:
    import module
    print("✅ Successfully imported module.py from utilities/ci")
except ImportError as e:
    print(f"❌ Failed to import module.py: {e}")

print_separator()

# Try to import from the root ci module
print("Testing imports from root ci module:")
try:
    # Add the root ci directory to the Python path
    root_ci_path = os.path.abspath(os.path.join(os.path.dirname(__file__), '..', '..'))
    sys.path.append(root_ci_path)
    print(f"Added {root_ci_path} to Python path")

    from ci.common.utils import create_gradle_container
    print("✅ Successfully imported create_gradle_container from ci.common.utils")
except ImportError as e:
    print(f"❌ Failed to import from ci.common.utils: {e}")

print_separator()

# Check if we're running in a pipenv environment
print("Checking pipenv environment:")
if os.environ.get('PIPENV_ACTIVE') == '1':
    print("✅ Running in a pipenv environment")
    print(f"Virtual environment: {os.environ.get('VIRTUAL_ENV', 'Unknown')}")
else:
    print("❌ Not running in a pipenv environment")
    print("To run in the pipenv environment, use: pipenv run python test_module.py")

print_separator()

# Print Python path for debugging
print("Python path:")
for path in sys.path:
    print(f"  - {path}")

if __name__ == "__main__":
    print_separator()
    print("Test complete!")
