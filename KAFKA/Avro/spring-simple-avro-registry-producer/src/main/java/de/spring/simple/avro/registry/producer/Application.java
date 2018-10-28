package de.spring.simple.avro.registry.producer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import de.spring.simple.avro.registry.Joe;
import de.spring.simple.avro.registry.producer.service.ProducerService;

@SpringBootApplication
// @EnableSchemaRegistryClient
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner lookup(ProducerService producerService) {
		return args -> {
			Joe joe = Joe.newBuilder()
					.setName("Snake")
					.setSurname("Eyes")
					.setAge(33)
					.setClassified(true)
			        // V2 .setRegion("New York")
					.build();
			producerService.sendJoe(joe);
		};
	}
}
