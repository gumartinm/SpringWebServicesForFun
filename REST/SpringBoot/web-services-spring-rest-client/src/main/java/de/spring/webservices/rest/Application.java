package de.spring.webservices.rest;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import de.spring.webservices.rest.domain.service.BusinessService;

@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }


  @Bean
  CommandLineRunner lookup(BusinessService businessService) {
    return args -> {
    	businessService.doSomethingWithCars();
        
    	businessService.doSomethingWithCar(66L);
        
    	businessService.createsNewCar();
    };
  }

}
