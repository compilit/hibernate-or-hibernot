package com.compilit.domain;

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

  public void authenticate(PasswordEncoder passwordEncoder, byte[] password) {
    if (passwordEncoder.matches(new String(password), this.password)) {
      var authentication = new UsernamePasswordAuthenticationToken(email, null);
      SecurityContextHolder.getContext().setAuthentication(authentication);
    } else {
      throw new UnauthorizedException("Invalid credentials");
    }
  }

  public record DTO(String email, String password) {}

}
