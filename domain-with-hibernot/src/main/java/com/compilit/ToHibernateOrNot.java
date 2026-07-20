package com.compilit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.testcontainers.postgresql.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

@SpringBootApplication
public class ToHibernateOrNot {

  static void main(String[] args) throws Exception {
//    System.setProperty("spring.devtools.restart.enabled", "false");
    try (var postgres = new PostgreSQLContainer(
      DockerImageName.parse("postgis/postgres:17-master").asCompatibleSubstituteFor("postgres"))
      .withDatabaseName("tohibernate")
      .withUsername("sa")
      .withPassword("sa")
      .withInitScript("init-user.sql")) {

      postgres.start();
      Runtime.getRuntime().addShutdownHook(new Thread(postgres::stop));
      System.setProperty("spring.datasource.url", postgres.getJdbcUrl());
      System.setProperty("spring.datasource.username", postgres.getUsername());
      System.setProperty("spring.datasource.password", postgres.getPassword());
      SpringApplication.main(args);
    }
  }

}
