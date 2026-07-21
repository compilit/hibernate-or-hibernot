package com.compilit.domain.api;

public interface SecurityContext {

  String getPrincipal();

  String encodePassword(String password);
}