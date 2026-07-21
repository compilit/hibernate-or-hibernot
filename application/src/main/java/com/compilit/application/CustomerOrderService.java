package com.compilit.application;

import com.compilit.domain.Order;
import com.compilit.domain.OrderLine;
import com.compilit.domain.OrderRepository;
import com.compilit.domain.api.OrderDTO;
import com.compilit.domain.api.OrderService;
import com.compilit.domain.api.SecurityContext;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
class CustomerOrderService implements OrderService {

  private final OrderRepository orderRepository;
  private final SecurityContext securityContext;

  public CustomerOrderService(OrderRepository orderRepository, SecurityContext securityContext) {
    this.orderRepository = orderRepository;
    this.securityContext = securityContext;
  }

  @Override
  public void placeOrder(OrderDTO newOrder) {
    var customerId = securityContext.getPrincipal();

    var order = new Order(customerId);

    newOrder.orderLines().stream()
            .map(OrderLine::from)
            .forEach(order::addOrderLine);

    orderRepository.save(order);
  }

  @Override
  public List<OrderDTO> showOrders() {
    var customerId = securityContext.getPrincipal();
    return orderRepository.findAllByCustomerId(customerId)
                          .stream()
                          .map(Order::toDTO)
                          .toList();
  }
}