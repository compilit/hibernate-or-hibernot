package com.compilit.infrastructure.config;

import com.compilit.domain.api.ApplicationProperties;
import com.compilit.domain.api.CustomerDTO;
import com.compilit.domain.api.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
class AdminUserInitializer {

  private final ApplicationProperties applicationProperties;
  private final UserService userService;

  AdminUserInitializer(ApplicationProperties applicationProperties, UserService userService) {
    this.applicationProperties = applicationProperties;
    this.userService = userService;
  }

  @PostConstruct
  private void initAdminAccount() {
    var adminUsername = applicationProperties.getAdminUsername();
    var adminPassword = applicationProperties.getAdminPassword();
    if (!userService.exists(adminUsername)) {
      userService.createUser(new CustomerDTO(adminUsername, adminPassword));
    }
  }
}
