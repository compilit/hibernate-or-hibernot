package com.compilit.infrastructure;

import com.compilit.domain.api.SecurityContext;
import com.github.dockerjava.api.exception.UnauthorizedException;
import java.util.Optional;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

@Component
class SecurityContextAdapter implements SecurityContext {

  private final PasswordEncoder passwordEncoder;

  SecurityContextAdapter(PasswordEncoder passwordEncoder) {
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public String getPrincipal() {
    return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                   .map(Authentication::getPrincipal)
                   .map(Object::toString)
                   .orElse(null);
  }

  @Override
  public void authenticate(String email, byte[] rawPassword, String encodedPassword) {
    if (passwordEncoder.matches(new String(rawPassword), encodedPassword)) {
      var authentication = new UsernamePasswordAuthenticationToken(email, null);
      SecurityContextHolder.getContext().setAuthentication(authentication);
    } else {
      throw new UnauthorizedException("Invalid credentials");
    }
  }

  @Override
  public String encodePassword(String password) {
    return passwordEncoder.encode(password);
  }

}