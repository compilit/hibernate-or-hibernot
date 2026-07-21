package com.compilit.domain;

import com.compilit.domain.api.OrderLineDTO;
import java.util.UUID;
import org.springframework.data.annotation.Id;

public class OrderLine {

  @Id
  private UUID id;
  private final String productName;
  private final double price;
  private final int amount;

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