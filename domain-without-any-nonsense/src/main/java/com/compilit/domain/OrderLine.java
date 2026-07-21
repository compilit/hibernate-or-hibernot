package com.compilit.domain;

import com.compilit.domain.api.OrderLineDTO;
import java.util.UUID;

public class OrderLine {

  private UUID id;
  private String productName;
  private double price;
  private int amount;

  public OrderLine(UUID id, String productName, double price, int amount) {
    this.id = id;
    this.productName = productName;
    this.price = price;
    this.amount = amount;
  }

  public OrderLineDTO toDTO() {
    return new OrderLineDTO(productName, price, amount);
  }

  public UUID getId() {
    return id;
  }

  public static OrderLine from(OrderLineDTO dto) {
    return new OrderLine(null, dto.productName(), dto.price(), dto.amount());
  }

}