package com.compilit.domain;

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

  public DTO toDTO() {
    return new DTO(orderLines.stream().map(OrderLine::toDTO).toList());
  }

  public record DTO(List<OrderLine.DTO> orderLines) {}
}