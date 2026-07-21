package com.compilit.domain;

public class Customer {

  private final String email;
  private String password;
  //User related metadata...

  public Customer(String email, String password) {
    this.email = email;
    this.password = password;
  }

  public void changePassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }
}
