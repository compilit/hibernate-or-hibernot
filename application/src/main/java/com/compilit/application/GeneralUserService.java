package com.compilit.application;

import com.compilit.domain.User;
import com.compilit.domain.UserRepository;
import com.compilit.domain.api.SecurityContext;import com.compilit.domain.api.UserDTO;
import com.compilit.domain.api.UserService;
import org.springframework.stereotype.Service;

@Service
class GeneralUserService implements UserService {

  private final UserRepository userRepository;
  private final SecurityContext securityContext;

  GeneralUserService(UserRepository userRepository, SecurityContext securityContext) {
    this.userRepository = userRepository;
    this.securityContext = securityContext;
  }

  @Override
  public void createUser(UserDTO user) {
    var encodedPassword = securityContext.encodePassword(user.password());
    var newUser = new User(user.email(), encodedPassword);
    userRepository.save(newUser);
  }

  @Override
  public void authenticateUser(String username, byte[] password) {
    userRepository.findById(username).ifPresent(user -> {
      user.authenticate(securityContext, password);
    });
  }
}
