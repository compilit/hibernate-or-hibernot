package com.compilit.presentation;

import com.compilit.domain.User;
import com.compilit.domain.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
class UserController {

  private final UserService userService;

  UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping
  public void createUser(@RequestBody User.DTO user) {
    userService.createUser(user);
  }
}
