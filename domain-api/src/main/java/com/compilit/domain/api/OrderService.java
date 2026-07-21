package com.compilit.domain.api;

import java.util.List;

public interface OrderService {
  void placeOrder(OrderDTO order);
  List<OrderDTO> showOrders();
}