package com.compilit.domain;

import com.compilit.domain.api.CustomerDto;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Customer {

  @Id
  private String email;
  private String password;
  //User related metadata...

  public Customer() {
  }

  public Customer(String email, String password) {
    this.email = email;
    this.password = password;
  }

  public void changePassword(String password) {
    this.password = password;
  }

  public CustomerDto toCustomerDTO() {
    return new CustomerDto(email, password);
  }
}
