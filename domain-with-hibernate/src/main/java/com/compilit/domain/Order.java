package com.compilit.domain;

import com.compilit.domain.api.OrderDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  private String customerId;
  @OneToMany(targetEntity = OrderLine.class)
  private final List<OrderLine> orderLines = new ArrayList<>();

  public Order() {}
  public Order(String customerId) {
    this.customerId = customerId;
  }

  public void addOrderLine(OrderLine orderLine) {
    this.orderLines.add(orderLine);
  }

  public OrderDTO toDTO() {
    return new OrderDTO(orderLines.stream().map(OrderLine::toDTO).toList());
  }

  public String getCustomerId() {
    return customerId;
  }

  public UUID getId() {
    return id;
  }
}