import sys
import dagger
import os
import sys

# Add the root ci directory to the Python path
sys.path.append(os.path.abspath(os.path.join(os.path.dirname(__file__), '..')))
from common.utils import create_gradle_container, build_module

# Release pipeline
async def release_pipeline():
    # Initialize Dagger client
    async with dagger.Connection() as client:
        # Get reference to the source code
        source = client.host().directory(".", exclude=["ci/__pycache__"])
        
        # Create a container for building the project
        builder = create_gradle_container(client, source)
        
        # Build all modules
        build = builder.with_exec(["./gradlew", "build"])
        
        # Create distribution
        dist = build.with_exec(["./gradlew", "assemble"])
        
        # Get distribution artifacts
        artifacts = dist.directory("build/libs")
        
        # Export artifacts to host
        await artifacts.export("./build/libs")
        
        # Print success message
        print("Release pipeline completed successfully!")

if __name__ == "__main__":
    import asyncio
    asyncio.run(release_pipeline())
