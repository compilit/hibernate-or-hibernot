package com.compilit.domain.api;

import java.util.Optional;

public interface UserService {

  void createUser(CustomerDTO user);

  boolean exists(String username);

  Optional<CustomerDTO> find(String username);
}
