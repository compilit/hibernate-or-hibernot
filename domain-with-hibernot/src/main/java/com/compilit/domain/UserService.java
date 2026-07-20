package com.compilit.domain;

public interface UserService {

  void createUser(User.DTO user);

  void authenticateUser(String username, byte[] password);
}
