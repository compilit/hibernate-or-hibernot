package com.compilit.domain;

import java.util.List;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, UUID> {

  List<Order> findAllByCustomerId(String customerId);
}
