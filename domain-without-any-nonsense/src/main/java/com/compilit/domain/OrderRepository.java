package com.compilit.domain;

import java.util.List;

public interface OrderRepository {

  void save(OrderOverview orderOverview);
  List<OrderOverview> findAllByCustomerId(String customerId);
}
