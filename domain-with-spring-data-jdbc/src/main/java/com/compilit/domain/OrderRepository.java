package com.compilit.domain;

import java.util.List;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<OrderOverview, UUID> {

  List<OrderOverview> findAllByCustomerId(String customerId);
}
