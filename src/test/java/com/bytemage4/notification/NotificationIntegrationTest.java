package com.bytemage4.notification;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

/**
 * Integration tests using Testcontainers with real MySQL database.
 * These tests are slower but provide better confidence for database operations.
 */
@SpringBootTest
@ActiveProfiles("integration")
@Testcontainers
class NotificationIntegrationTest {

    @Container
    @ServiceConnection
    static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.0");

    @Test
    void contextLoadsWithMySQLContainer() {
        // This test verifies the application works with a real MySQL database
        // Add your integration tests here
    }
}

