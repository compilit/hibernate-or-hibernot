package com.compilit.regression;

import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.compilit.PostgresContainerFactory;
import com.compilit.ToHibernateOrNot;
import com.compilit.domain.api.ApplicationProperties;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.postgresql.PostgreSQLContainer;

/**
 * Just a simple regression test to verify the darn thing actually works.
 */
@Testcontainers
@SpringBootTest(classes = ToHibernateOrNot.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class CompilitRegressionTest {

  @Autowired
  private ApplicationProperties applicationProperties;

  private static final String TEST_CUSTOMER_EMAIL = "Marty";
  private static final String TEST_CUSTOMER_PASSWORD = "McFly";

  @Container
  static final PostgreSQLContainer postgres = PostgresContainerFactory.newContainer();

  @DynamicPropertySource
  static void datasourceProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", postgres::getJdbcUrl);
    registry.add("spring.datasource.username", postgres::getUsername);
    registry.add("spring.datasource.password", postgres::getPassword);
  }

  @Autowired
  private MockMvc mockMvc;

  @Test
  void createUser_withValidPayload_isAccepted() throws Exception {
    var payload = readPayload("create-user-request.json");

    mockMvc.perform(post("/users")
                      .header("Authorization",
                              basicAuthHeader(
                                applicationProperties.getAdminUsername(),
                                applicationProperties.getAdminPassword()
                              )
                      )
                      .contentType(MediaType.APPLICATION_JSON)
                      .content(payload))
           .andExpect(status().is2xxSuccessful());
  }

  @Test
  void placeOrder_asAuthenticatedCustomer_isAccepted() throws Exception {
    var payload = readPayload("place-order-request.json");

    mockMvc.perform(post("/orders")
                      .header("Authorization", basicAuthHeader(TEST_CUSTOMER_EMAIL, TEST_CUSTOMER_PASSWORD))
                      .contentType(MediaType.APPLICATION_JSON)
                      .content(payload))
           .andExpect(status().is2xxSuccessful());
  }

  @Test
  void placeOrder_withoutAuthentication_isRejected() throws Exception {
    var payload = readPayload("place-order-request.json");

    mockMvc.perform(post("/orders")
                      .contentType(MediaType.APPLICATION_JSON)
                      .content(payload))
           .andExpect(status().isUnauthorized());
  }

  @Test
  void showOrders_returnsOrdersPlacedByTheAuthenticatedCustomer() throws Exception {
    var payload = readPayload("place-order-request.json");

    mockMvc.perform(post("/orders")
                      .header("Authorization", basicAuthHeader(TEST_CUSTOMER_EMAIL, TEST_CUSTOMER_PASSWORD))
                      .contentType(MediaType.APPLICATION_JSON)
                      .content(payload))
           .andExpect(status().is2xxSuccessful());

    mockMvc.perform(get("/orders")
                      .header("Authorization", basicAuthHeader(TEST_CUSTOMER_EMAIL, TEST_CUSTOMER_PASSWORD)))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$..productName", hasItem("Coffee Mug")))
           .andExpect(jsonPath("$..productName", hasItem("Notebook")));
  }

  @Test
  void showOrders_withoutAuthentication_isRejected() throws Exception {
    mockMvc.perform(get("/orders"))
           .andExpect(status().isUnauthorized());
  }

  private static String readPayload(String fileName) {
    try {
      var resource = new ClassPathResource("payloads/" + fileName);
      return new String(
        resource.getInputStream()
                .readAllBytes(), StandardCharsets.UTF_8
      );
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

  private static String basicAuthHeader(String username, String password) {
    var credentials = username + ":" + password;
    return "Basic " + Base64.getEncoder()
                            .encodeToString(credentials.getBytes(StandardCharsets.UTF_8));
  }
}