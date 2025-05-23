#!/usr/bin/env python
"""
Test script to verify that the root ci module is properly set up.
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

# Try to import from the ci module
print("Testing imports from root ci module:")
try:
    from common.utils import create_gradle_container
    print("✅ Successfully imported create_gradle_container from common.utils")
except ImportError as e:
    print(f"❌ Failed to import from common.utils: {e}")

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
