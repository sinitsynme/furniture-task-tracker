package ru.sinitsynme.furnituretasktracker

import org.junit.jupiter.api.BeforeAll
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.utility.DockerImageName
import org.testcontainers.utility.MountableFile

@AutoConfigureTestDatabase(replace = NONE)
abstract class AbstractIntegrationTest {

    companion object {

        private val postgres: PostgreSQLContainer<*> = PostgreSQLContainer(DockerImageName.parse("postgres:17.2"))
            .apply {
                this.withDatabaseName("furniture-count-db")
                    .withUsername("postgres")
                    .withPassword("postgres")
                    .withCopyToContainer(
                        MountableFile.forClasspathResource("/schema.sql"),
                        "/docker-entrypoint-initdb.d/init.sql"
                    )
            }

        fun jdbcUrl(): String {
            return "jdbc:postgresql://${postgres.host}:${postgres.getMappedPort(PostgreSQLContainer.POSTGRESQL_PORT)}/${postgres.databaseName}?currentSchema=furniture_task_tracker"
        }

        @JvmStatic
        @DynamicPropertySource
        fun properties(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url", Companion::jdbcUrl)
            registry.add("spring.datasource.username", postgres::getUsername)
            registry.add("spring.datasource.password", postgres::getPassword)
        }

        @JvmStatic
        @BeforeAll
        internal fun setUp() {
            postgres.start()
        }
    }
}