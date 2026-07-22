package com.compilit.domain;

import com.compilit.domain.api.ProductDto;

public record Product(String name, double price) {

  public ProductDto toDTO() {
    return new ProductDto(name, price);
  }
}
