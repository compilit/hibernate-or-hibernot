package com.compilit.domain;

import com.compilit.domain.api.SecurityContext;
import com.github.dockerjava.api.exception.UnauthorizedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class User {

  private final String email;
  private String password;
  //User related metadata...

  public User(String email, String password) {
    this.email = email;
    this.password = password;
  }

  public void authenticate(SecurityContext securityContext, byte[] password) {
    securityContext.authenticate(this.email, password, this.password);
  }

}
