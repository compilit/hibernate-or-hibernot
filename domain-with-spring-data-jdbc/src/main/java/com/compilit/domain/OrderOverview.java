package com.compilit.domain;

import com.compilit.domain.api.OrderDto;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;

public class OrderOverview {

  @Id
  private final UUID id;
  private final String customerId;
  private final Instant createdAt;

  @MappedCollection(idColumn = "order_overview_id")
  private final Set<OrderLine> orderLines;

  public OrderOverview(UUID id, String customerId, Instant createdAt, Set<OrderLine> orderLines) {
    this.id = id;
    this.customerId = customerId;
    this.createdAt = createdAt;
    this.orderLines = new LinkedHashSet<>(orderLines);
  }

  public void addOrderLine(OrderLine orderLine) {
    this.orderLines.add(orderLine);
  }

  public OrderDto toDTO() {
    return new OrderDto(
      id,
      orderLines.stream()
                .map(OrderLine::toDTO)
                .toList(),
      createdAt.toString()
    );
  }

  public UUID getId() {
    return id;
  }

  public String getCustomerId() {
    return customerId;
  }

}