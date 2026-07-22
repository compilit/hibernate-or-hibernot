package com.compilit.application;

import com.compilit.domain.OrderLine;
import com.compilit.domain.OrderOverview;
import com.compilit.domain.OrderRepository;
import com.compilit.domain.ProductRepository;
import com.compilit.domain.api.ApplicationProperties;
import com.compilit.domain.api.NewOrderDto;
import com.compilit.domain.api.OrderDto;
import com.compilit.domain.api.OrderService;
import com.compilit.domain.api.SecurityContext;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
class ProductOrderService implements OrderService {

  private final OrderRepository orderRepository;
  private final SecurityContext securityContext;
  private final ProductRepository productRepository;

  public ProductOrderService(OrderRepository orderRepository,
                             SecurityContext securityContext,
                             ProductRepository productRepository
  ) {
    this.orderRepository = orderRepository;
    this.securityContext = securityContext;
    this.productRepository = productRepository;
  }

  @Override
  public void placeOrder(NewOrderDto newOrder) {
    var existingProducts = productRepository.getProducts();
    newOrder.orderLines().forEach(orderLine -> {
      if (existingProducts.stream().noneMatch(product -> Objects.equals(product.name(), orderLine.productName()))) {
        throw new IllegalArgumentException("Product " + orderLine.productName() + " does not exist");
      }
    });

    var customerId = securityContext.getPrincipal();

    var order = new OrderOverview(
      null,
      customerId,
      Instant.now(),
      newOrder.orderLines()
              .stream()
              .map(OrderLine::from)
              .collect(Collectors.toUnmodifiableSet())
    );

    orderRepository.save(order);
  }

  @Override
  public List<OrderDto> showOrders() {
    var customerId = securityContext.getPrincipal();
    return orderRepository.findAllByCustomerId(customerId)
                          .stream()
                          .map(OrderOverview::toDTO)
                          .toList();
  }
}