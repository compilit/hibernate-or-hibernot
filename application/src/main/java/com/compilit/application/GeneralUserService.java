package com.compilit.application;

import com.compilit.domain.Customer;
import com.compilit.domain.CustomerRepository;
import com.compilit.domain.api.CustomerDto;
import com.compilit.domain.api.SecurityContext;
import com.compilit.domain.api.UserService;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
class GeneralUserService implements UserService {

  private final CustomerRepository userRepository;
  private final SecurityContext securityContext;

  GeneralUserService(
    CustomerRepository userRepository,
    SecurityContext securityContext
  ) {
    this.userRepository = userRepository;
    this.securityContext = securityContext;
  }

  @Override
  public void createUser(CustomerDto user) {
    var encodedPassword = securityContext.encodePassword(user.password());
    var newUser = new Customer(user.email(), encodedPassword);
    userRepository.save(newUser);
  }

  @Override
  public boolean exists(String username) {
    return userRepository.existsById(username);

  }

  @Override
  public Optional<CustomerDto> find(String username) {
    return userRepository.findById(username)
                         .map(Customer::toCustomerDTO);
  }
}
