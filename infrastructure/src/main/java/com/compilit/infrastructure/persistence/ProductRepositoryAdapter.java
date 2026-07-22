package com.compilit.infrastructure.persistence;

import com.compilit.domain.Product;
import com.compilit.domain.ProductRepository;
import com.compilit.domain.api.ApplicationProperties;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
class ProductRepositoryAdapter implements ProductRepository {

  private final ApplicationProperties applicationProperties;

  ProductRepositoryAdapter(ApplicationProperties applicationProperties) {
    this.applicationProperties = applicationProperties;
  }

  @Override
  public Set<Product> getProducts() {
    return applicationProperties.getProducts()
                                .stream()
                                .map(productDto -> new Product(productDto.name(), productDto.price()))
                                .collect(Collectors.toUnmodifiableSet());
  }
}
