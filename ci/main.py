import sys
import dagger

# Main CI pipeline for the entire project
async def main():
    # Initialize Dagger client
    async with dagger.Connection() as client:
        # Get reference to the source code
        source = client.host().directory(".", exclude=["ci/__pycache__"])

        # Create a container for building the project
        builder = (client.container()
            .from_("gradle:8.11.1-jdk21")
            .with_directory("/src", source)
            .with_workdir("/src"))

        # Run tests
        test = builder.with_exec(["./gradlew", "test"])

        # Get test results
        test_results = test.directory("build/reports/tests")

        # Export test results to host
        await test_results.export("./build/reports/tests")

        # Print success message
        print("CI pipeline completed successfully!")

if __name__ == "__main__":
    import asyncio
    asyncio.run(main())
