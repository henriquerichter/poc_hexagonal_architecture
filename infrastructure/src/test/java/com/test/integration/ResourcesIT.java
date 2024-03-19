package com.test.integration;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;

import io.restassured.RestAssured;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class ResourcesIT {

  @LocalServerPort
  private Integer port;

  private static MySQLContainer<?> mySQLContainer;

  static {
    mySQLContainer = new MySQLContainer<>("mysql:8.3.0")
        .withDatabaseName("test_db")
        .withUsername("test")
        .withPassword("test")
        .withInitScript("schema.sql")
        .withReuse(true);

    mySQLContainer.start();
  }

  @DynamicPropertySource
  private static void properties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", mySQLContainer::getJdbcUrl);
    registry.add("spring.datasource.username", mySQLContainer::getUsername);
    registry.add("spring.datasource.password", mySQLContainer::getPassword);
  }

  @BeforeEach
  void setUp() {
    RestAssured.baseURI = "http://localhost:" + port;
  }
}
