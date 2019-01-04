package de.example.spring.pact.consumer.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import de.example.spring.pact.consumer.infrastructure.mapper.CarMapper;

@Configuration
public class MapperConfiguration {

	@Bean
	public CarMapper carMapper() {
		return new CarMapper();
	}
}
