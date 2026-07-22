package com.compilit.domain;

import com.compilit.domain.api.OrderLineDto;
import java.util.UUID;

public class OrderLine {

  private UUID id;
  private String productName;
  private int amount;

  public OrderLine(UUID id, String productName, int amount) {
    this.id = id;
    this.productName = productName;
    this.amount = amount;
  }

  public OrderLineDto toDTO() {
    return new OrderLineDto(productName, amount);
  }

  public UUID getId() {
    return id;
  }

  public static OrderLine from(OrderLineDto dto) {
    return new OrderLine(null, dto.productName(), dto.amount());
  }

}