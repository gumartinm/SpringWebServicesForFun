package de.example.spring.pact.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import de.example.spring.pact.domain.service.CarServiceImpl;

@Configuration
public class ServiceConfiguration {
	
	@Bean
	public CarServiceImpl carServiceImpl() {
		return new CarServiceImpl();
	}
}
