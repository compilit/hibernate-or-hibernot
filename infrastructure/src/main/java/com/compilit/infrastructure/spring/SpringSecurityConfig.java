package com.compilit.infrastructure.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
class SpringSecurityConfig {

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) {
    return http.authorizeHttpRequests(request -> request.anyRequest().authenticated())
               .httpBasic(Customizer.withDefaults())
               .csrf(AbstractHttpConfigurer::disable)
               .build();
  }


  @Bean
  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }
}
