package com.compilit.domain;

import java.util.Optional;

public interface CustomerRepository {

  void save(Customer customer);
  Optional<Customer> findByCustomerId(String customerId);
}
