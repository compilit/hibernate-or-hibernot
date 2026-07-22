package com.compilit.domain.api;

import java.util.List;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("compilit")
public class ApplicationProperties {

  private Admin admin;
  private List<ProductDto> products;

  public String getAdminUsername() {
    return admin.username;
  }

  public String getAdminPassword() {
    return admin.password;
  }

  public List<ProductDto> getProducts() {
    return products;
  }

  void setAdmin(Admin admin) {
    this.admin = admin;
  }

  void setProducts(List<ProductDto> products) {
    this.products = products;
  }

  public record Admin(String username, String password) {}
}
