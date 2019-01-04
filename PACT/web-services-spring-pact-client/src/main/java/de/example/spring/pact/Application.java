package de.example.spring.pact;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import de.example.spring.pact.domain.service.CarServiceImpl;

@SpringBootApplication
@EnableFeignClients
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Bean
  CommandLineRunner lookup(CarServiceImpl carService) {
    return args ->
    	carService.findAll()
    		.stream()
    		.map(car -> {
				System.out.println(car.getBrand());
				System.out.println(car.getEngine());
                return car;
            });
  }
}
