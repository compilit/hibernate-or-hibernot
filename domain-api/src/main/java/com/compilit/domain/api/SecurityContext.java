package com.compilit.domain.api;

public interface SecurityContext {
  String getPrincipal();

  void authenticate(String email, byte[] rawPassword, String encodedPassword);

  String encodePassword(String password);
}