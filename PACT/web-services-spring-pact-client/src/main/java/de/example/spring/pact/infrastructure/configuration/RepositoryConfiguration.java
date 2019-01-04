package de.example.spring.pact.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import de.example.spring.pact.domain.repository.CarRepository;
import de.example.spring.pact.infrastructure.feign.client.CarFeignClient;
import de.example.spring.pact.infrastructure.repository.CarRepositoryImpl;

@Configuration
public class RepositoryConfiguration {

	@Bean
	public CarRepository carRepository(CarFeignClient carFeignClient) {
		return new CarRepositoryImpl(carFeignClient);
	}
}
