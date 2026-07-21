package com.compilit.domain.api;

public interface UserService {

  void createUser(UserDTO user);

  void authenticateUser(String username, byte[] password);
}
