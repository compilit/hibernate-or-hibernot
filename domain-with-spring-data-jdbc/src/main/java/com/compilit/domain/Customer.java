package com.compilit.domain;

import com.compilit.domain.api.CustomerDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

public class Customer {

  @Id
  private final String email;
  private String password;
  //User related metadata...

  @Version
  private Long version;

  public Customer(String email, String password) {
    this.email = email;
    this.password = password;
  }

  public void changePassword(String password) {
    this.password = password;
  }

  public CustomerDTO toCustomerDTO() {
    return new CustomerDTO(email, password);
  }
}
