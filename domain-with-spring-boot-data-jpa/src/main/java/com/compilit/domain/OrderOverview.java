package com.compilit.domain;

import com.compilit.domain.api.OrderDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import java.time.Instant;
import java.util.Set;
import java.util.UUID;

@Entity
public class OrderOverview {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  private String customerId;
  private Instant createdAt;

  @OneToMany(targetEntity = OrderLine.class, cascade = CascadeType.ALL)
  @JoinColumn(name = "order_overview_id", nullable = false)
  private Set<OrderLine> orderLines;

  public OrderOverview() {
    //This is required by hibernate... :( It has no further purpose and in my opinion can cause weird bugs.
    //It also makes it impossible to certain fields immutable.
  }

  public OrderOverview(UUID id, String customerId, Instant createdAt, Set<OrderLine> orderLines) {
    this.id = id;
    this.customerId = customerId;
    this.createdAt = createdAt;
    this.orderLines = orderLines;
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

  public String getCustomerId() {
    return customerId;
  }

  public UUID getId() {
    return id;
  }
}