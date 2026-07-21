package com.compilit.domain.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationProperties {

  private final String adminUsername;
  private final String adminPassword;

  ApplicationProperties(
    @Value("${compilit.admin.username}") String username,
    @Value("${compilit.admin.password}") String password
  ) {
    this.adminUsername = username;
    this.adminPassword = password;
  }

  public String getAdminUsername() {
    return adminUsername;
  }

  public String getAdminPassword() {
    return adminPassword;
  }
}
