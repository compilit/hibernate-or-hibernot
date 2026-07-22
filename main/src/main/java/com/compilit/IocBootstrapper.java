//package com.compilit;
//
//import com.compilit.domain.api.OrderService;
//import com.compilit.domain.api.UserService;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//class IocBootstrapper {
//
//  @Bean
//  public OrderService orderService(ApplicationContext applicationContext) {
//    return ApplicationModule.bootstrap(OrderService.class);
//  }
//
//  @Bean
//  public UserService userService(ApplicationContext applicationContext) {
//    return ApplicationModule.bootstrap(UserService.class);
//  }
//
//}
