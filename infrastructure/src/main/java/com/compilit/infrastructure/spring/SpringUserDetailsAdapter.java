package com.compilit.infrastructure.spring;

import com.compilit.domain.api.UserService;
import java.util.Collection;
import java.util.List;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
class SpringUserDetailsAdapter implements UserDetailsService {

  private final UserService userService;

  SpringUserDetailsAdapter(UserService userService) {
    this.userService = userService;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userService.find(username).map(user -> new UserDetails() {
        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
          return List.of();
        }

        @Override
        public @Nullable String getPassword() {
          return user.password();
        }

        @Override
        public String getUsername() {
          return user.email();
        }
      }).orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
  }

}
