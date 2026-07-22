//package com.compilit.application;
//
//import com.compilit.domain.api.IocContext;
//
//public final class ApplicationModule {
//
//  private ApplicationModule() {}
//
//  //This replaces the @Service/@Component annotations
//  //Just to illustrate that they are not required to use Spring Boot :)
//  public static void bootstrap(IocContext iocContext) {
//    iocContext.register(ProductOrderService.class);
//    iocContext.register(GeneralUserService.class);
//  }
//}
