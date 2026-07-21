package com.compilit.domain;

import com.compilit.domain.OrderLine;
import com.compilit.domain.api.OrderDTO;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

public class OrderOverview {

  private final UUID id;
  private final String customerId;
  private final Instant createdAt;
  private final List<OrderLine> orderLines;

  public OrderOverview(UUID id, String customerId, Instant createdAt, List<OrderLine> orderLines) {
    this.id = id;
    this.customerId = customerId;
    this.createdAt = createdAt;
    this.orderLines = orderLines;
  }

  public void addOrderLine(OrderLine orderLine) {
    this.orderLines.add(orderLine);
  }

  public OrderDTO toDTO() {
    return new OrderDTO(orderLines.stream()
                                  .map(OrderLine::toDTO)
                                  .toList());
  }

  public UUID getId() {
    return id;
  }

  public String getCustomerId() {
    return customerId;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }
}