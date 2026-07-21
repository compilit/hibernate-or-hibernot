package com.compilit.domain.api;

public record OrderLineDTO(String productName, double price, int amount) {
  //This exists because of the SRP: these objects have different reasons to change and different stakeholders.
}