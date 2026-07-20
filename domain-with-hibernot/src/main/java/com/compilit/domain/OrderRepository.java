package com.compilit.domain;

import com.compilit.domain.Order.DTO;
import java.util.List;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, UUID> {

  List<DTO> findAllByCustomerId(String customerId);
}
