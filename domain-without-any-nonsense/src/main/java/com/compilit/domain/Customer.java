package com.compilit.domain;

import com.compilit.domain.api.CustomerDto;

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

  public CustomerDto toDTO() {
    return new CustomerDto(email, password);
  }

}
