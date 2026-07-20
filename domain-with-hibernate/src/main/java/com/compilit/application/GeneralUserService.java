package com.compilit.application;

import com.compilit.domain.User;
import com.compilit.domain.UserRepository;
import com.compilit.domain.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
class GeneralUserService implements UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  GeneralUserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public void createUser(User.DTO user) {
    var encodedPassword = passwordEncoder.encode(user.password());
    var newUser = new User(user.email(), encodedPassword);
    userRepository.save(newUser);
  }

  @Override
  public void authenticateUser(String username, byte[] password) {
    userRepository.findById(username).ifPresent(user -> {
      user.authenticate(passwordEncoder, password);
    });
  }
}
