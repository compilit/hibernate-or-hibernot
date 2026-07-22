package com.compilit.domain.api;

import java.util.List;

public interface OrderService {
  void placeOrder(NewOrderDto order);
  List<OrderDto> showOrders();
}