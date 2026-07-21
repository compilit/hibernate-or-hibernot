package com.compilit.domain;

import com.compilit.domain.api.OrderDTO;
import java.util.ArrayList;
import java.util.List;

public class Order {

  private final String customerId;
  private final List<OrderLine> orderLines = new ArrayList<>();

  public Order(String customerId) {
    this.customerId = customerId;
  }

  public void addOrderLine(OrderLine orderLine) {
    this.orderLines.add(orderLine);
  }

  public OrderDTO toDTO() {
    return new OrderDTO(orderLines.stream().map(OrderLine::toDTO).toList());
  }

}