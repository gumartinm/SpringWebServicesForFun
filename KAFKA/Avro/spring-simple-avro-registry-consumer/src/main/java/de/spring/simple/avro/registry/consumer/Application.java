package de.spring.simple.avro.registry.consumer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import de.spring.simple.avro.registry.consumer.service.ConsumerService;

@SpringBootApplication
// @EnableSchemaRegistryClient
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner lookup(ConsumerService consumerService) {
		return args -> {
			consumerService.consume();
		};
	}
}
