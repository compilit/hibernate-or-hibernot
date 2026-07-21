package com.compilit.domain;

import com.compilit.domain.api.OrderDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity
public class OrderOverview {

  @Id
  private UUID id;
  private String customerId;
  private Instant createdAt;

  @OneToMany(targetEntity = OrderLine.class, cascade = CascadeType.ALL)
  @JoinColumn(name = "order_overview_id", nullable = false)
  private List<OrderLine> orderLines;

  public OrderOverview() {
  }

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

  public String getCustomerId() {
    return customerId;
  }

  public UUID getId() {
    return id;
  }
}