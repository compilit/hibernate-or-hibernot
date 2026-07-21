package com.compilit.presentation;

import com.compilit.domain.api.OrderDTO;
import com.compilit.domain.api.OrderService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

  private final OrderService orderService;

  public OrderController(OrderService orderService) {
    this.orderService = orderService;
  }

  @PostMapping
  public void placeOrder(@RequestBody OrderDTO order) {
    orderService.placeOrder(order);
  }

  @GetMapping
  public List<OrderDTO> showOrders() {
    return orderService.showOrders();
  }
}