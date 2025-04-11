import sys
import dagger
import os

# Add the root ci directory to the Python path
sys.path.append(os.path.abspath(os.path.join(os.path.dirname(__file__), '..', '..')))
from common.utils import create_gradle_container, build_module, test_module

# Utilities module CI pipeline
async def utilities_pipeline():
    # Initialize Dagger client
    async with dagger.Connection() as client:
        # Get reference to the source code
        source = client.host().directory(".", exclude=["ci/__pycache__"])

        # Create a container for building the project
        builder = create_gradle_container(client, source)

        # Build the utilities module
        build = build_module(builder, "utilities")

        # Run tests for the utilities module
        test = test_module(build, "utilities")

        # Get test results
        test_results = test.directory("utilities/build/reports/tests")

        # Export test results to host
        await test_results.export("./utilities/build/reports/tests")

        # Print success message
        print("Utilities module pipeline completed successfully!")

if __name__ == "__main__":
    import asyncio
    asyncio.run(utilities_pipeline())
