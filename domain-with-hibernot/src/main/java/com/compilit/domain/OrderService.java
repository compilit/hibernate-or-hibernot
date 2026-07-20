package com.compilit.domain;

import com.compilit.domain.Order.DTO;
import java.util.List;

public interface OrderService {
  void placeOrder(Order.DTO order);
  List<DTO> showOrders();
}