package com.compilit.infrastructure;

import com.compilit.domain.SecurityContext;
import java.util.Optional;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

@Component
public class UserSecurityContext implements SecurityContext {

  @Override
  public String getPrincipal() {
    return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                   .map(Authentication::getPrincipal)
                   .map(Object::toString)
                   .orElse(null);
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    return http.authorizeHttpRequests(request -> request.anyRequest()
                                                        .authenticated())
               .httpBasic(Customizer.withDefaults())
               .build();
  }


  @Bean
  public PasswordEncoder passwordEncoder() {
    PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    return encoder;
  }
}