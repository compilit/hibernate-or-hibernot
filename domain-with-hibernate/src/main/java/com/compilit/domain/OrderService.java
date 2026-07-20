package com.compilit.domain;

import java.util.List;

public interface OrderService {
  void placeOrder(Order.DTO order);
  List<Order.DTO> showOrders();
}