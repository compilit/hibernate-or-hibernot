package com.compilit.domain;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderOverview, UUID> {

  List<OrderOverview> findAllByCustomerId(String customerId);
}
