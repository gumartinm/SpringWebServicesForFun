package de.example.spring.pact.consumer.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import de.example.spring.pact.consumer.domain.repository.CarRepository;
import de.example.spring.pact.consumer.domain.service.impl.CarServiceImpl;

@Configuration
public class ServiceConfiguration {

	@Bean
	public CarServiceImpl carServiceImpl(CarRepository carRepository) {
		return new CarServiceImpl(carRepository);
	}
}
