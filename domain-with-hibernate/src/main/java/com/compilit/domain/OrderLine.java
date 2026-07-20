package com.compilit.domain;

public record OrderLine(String productName, double price, int amount) {

  public DTO toDTO() {
    return new DTO(productName, price, amount);
  }

  public static OrderLine from(DTO dto) {
    return new OrderLine(dto.productName(), dto.price(), dto.amount());
  }

  public record DTO(String productName, double price, int amount) {
    //This exists because of the SRP: these objects have different reasons to change and different stakeholders.
  }
}