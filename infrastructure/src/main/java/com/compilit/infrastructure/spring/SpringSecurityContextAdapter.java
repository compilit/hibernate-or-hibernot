package com.compilit.infrastructure.spring;

import com.compilit.domain.api.SecurityContext;
import java.util.Optional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
class SpringSecurityContextAdapter implements SecurityContext {

  private final PasswordEncoder passwordEncoder;

  SpringSecurityContextAdapter(PasswordEncoder passwordEncoder) {
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public String getPrincipal() {
    return Optional.ofNullable(SecurityContextHolder.getContext()
                                                    .getAuthentication())
                   .map(Authentication::getPrincipal)
                   .map(it -> (UserDetails) it)
                   .map(UserDetails::getUsername)
                   .orElse(null);
  }

  @Override
  public String encodePassword(String password) {
    return passwordEncoder.encode(password);
  }

}