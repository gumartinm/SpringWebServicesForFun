package de.example.spring.pact.consumer.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import de.example.spring.pact.consumer.domain.repository.CarRepository;
import de.example.spring.pact.consumer.infrastructure.feign.client.CarFeignClient;
import de.example.spring.pact.consumer.infrastructure.mapper.CarMapper;
import de.example.spring.pact.consumer.infrastructure.repository.impl.CarRepositoryImpl;

@Configuration
public class RepositoryConfiguration {

	@Bean
	public CarRepository carRepository(CarFeignClient carFeignClient, CarMapper carMapper) {
		return new CarRepositoryImpl(carFeignClient, carMapper);
	}
}
