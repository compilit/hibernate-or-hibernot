package com.compilit.domain;

import com.compilit.domain.Order;
import java.util.ArrayList;
import java.util.List;

public class Customer {

  private final String id;
  private List<Order> orders;

  public Customer(String id) {
    this(id, new  ArrayList<>());
  }

  public Customer(String id, List<Order> orders) {
    this.id = id;
    this.orders = orders;
  }

  public void placeOrder(Order order) {
    this.orders.add(order);
  }

  public record DTO(List<Order.DTO> orders) {
  }

}
