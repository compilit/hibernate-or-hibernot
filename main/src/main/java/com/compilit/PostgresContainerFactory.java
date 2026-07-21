package com.compilit;

import org.testcontainers.postgresql.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

/**
 * This class only exists because we're using testcontainers in production code.
 * It acts as a Single Point of Definition for both the tests and the
 */
public final class PostgresContainerFactory {

  private PostgresContainerFactory() {
  }

  public static PostgreSQLContainer newContainer() {
    var postgresImage = DockerImageName.parse("postgis/postgres:17-master")
                                  .asCompatibleSubstituteFor("postgres");
    try (var container = new PostgreSQLContainer(postgresImage)
      .withDatabaseName("tohibernate")
      .withUsername("sa")
      .withPassword("sa")
      .withInitScript("init-user.sql")) {
      return container;
    }
  }
}