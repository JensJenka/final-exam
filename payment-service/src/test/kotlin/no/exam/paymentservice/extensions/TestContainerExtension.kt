package no.exam.orderservice.extensions

import org.junit.jupiter.api.extension.AfterAllCallback
import org.junit.jupiter.api.extension.BeforeAllCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.testcontainers.containers.GenericContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.utility.DockerImageName

@Testcontainers
class TestContainerExtension : BeforeAllCallback, AfterAllCallback {

    private val redisDockerImage = DockerImageName.parse("redis:latest")
    private val dynaliteDockerImage = DockerImageName.parse("dlsniper/dynalite")

    @Container
    private val dynaliteContainer = GenericContainer(dynaliteDockerImage)

    @Container
    private val redisContainer = GenericContainer(redisDockerImage)

    override fun beforeAll(context: ExtensionContext?) {
        redisContainer.start()
        dynaliteContainer.start()
    }

    override fun afterAll(context: ExtensionContext?) {
        redisContainer.stop()
        dynaliteContainer.stop()
    }
}
