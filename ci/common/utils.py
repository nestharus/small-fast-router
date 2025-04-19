import dagger

# Common utility functions for CI pipelines

def create_gradle_container(client, source):
    """
    Creates a Gradle container with the project source code
    """
    return (client.container()
        .from_("gradle:8.13-jdk23")
        .with_directory("/src", source)
        .with_workdir("/src"))

def build_module(container, module_path):
    """
    Runs Gradle build for a specific module
    """
    return container.with_exec(["./gradlew", f"{module_path}:build"])

def test_module(container, module_path):
    """
    Runs tests for a specific module
    """
    return container.with_exec(["./gradlew", f"{module_path}:test"])
