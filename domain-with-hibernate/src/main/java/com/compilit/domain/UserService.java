package com.compilit.domain;

import com.compilit.domain.User;

public interface UserService {

  void createUser(User.DTO user);

  void authenticateUser(String username, byte[] password);
}
