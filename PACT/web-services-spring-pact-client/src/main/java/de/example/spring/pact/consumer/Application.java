package de.example.spring.pact.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import de.example.spring.pact.consumer.domain.service.impl.CarServiceImpl;

@SpringBootApplication
@EnableFeignClients
public class Application {
	Logger logger = LoggerFactory.getLogger(Application.class);

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Bean
  CommandLineRunner lookup(CarServiceImpl carService) {
    return args ->
    	carService.findAll()
    		.stream()
    		.map(car -> {
    			logger.info(String.format("CAR BRAND: %s", car.getBrand()));
    			logger.info(String.format("CAR ENGINE: %s", car.getEngine()));
                return car;
            });
  }
}
