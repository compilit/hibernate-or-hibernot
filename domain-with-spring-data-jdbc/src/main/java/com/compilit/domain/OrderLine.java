package com.compilit.domain;

import com.compilit.domain.api.NewOrderLineDto;
import com.compilit.domain.api.OrderLineDto;
import java.util.UUID;
import org.springframework.data.annotation.Id;

public class OrderLine {

  @Id
  private UUID id;
  private final String productName;
  private final int amount;

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

  public static OrderLine from(NewOrderLineDto dto) {
    return new OrderLine(null, dto.productName(), dto.amount());
  }

}