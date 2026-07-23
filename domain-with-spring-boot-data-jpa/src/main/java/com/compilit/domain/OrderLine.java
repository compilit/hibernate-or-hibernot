package com.compilit.domain;

import com.compilit.domain.api.NewOrderLineDto;
import com.compilit.domain.api.OrderLineDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.UUID;

@Entity
public class OrderLine {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;
  private String productName;
  private int amount;

  public OrderLine() {
  }

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