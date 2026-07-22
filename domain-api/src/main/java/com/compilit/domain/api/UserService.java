package com.compilit.domain.api;

import java.util.Optional;

public interface UserService {

  void createUser(CustomerDto user);

  boolean exists(String username);

  Optional<CustomerDto> find(String username);
}
