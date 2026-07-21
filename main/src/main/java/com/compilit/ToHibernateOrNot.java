package com.compilit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.compilit")
public class ToHibernateOrNot {

  static void main(String[] args) {
    try (var postgres = PostgresContainerFactory.newContainer()) {
      postgres.start();
      Runtime.getRuntime().addShutdownHook(new Thread(postgres::stop));
      System.setProperty("spring.datasource.url", postgres.getJdbcUrl());
      System.setProperty("spring.datasource.username", postgres.getUsername());
      System.setProperty("spring.datasource.password", postgres.getPassword());
      SpringApplication.run(ToHibernateOrNot.class, args);
    }
  }

}
