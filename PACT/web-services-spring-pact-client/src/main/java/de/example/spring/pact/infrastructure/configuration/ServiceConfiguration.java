package de.example.spring.pact.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import de.example.spring.pact.domain.service.CarServiceImpl;
import de.example.spring.pact.infrastructure.repository.CarRepository;

@Configuration
public class ServiceConfiguration {

	@Bean
	public CarServiceImpl carServiceImpl(CarRepository carRepository) {
		return new CarServiceImpl(carRepository);
	}
}
