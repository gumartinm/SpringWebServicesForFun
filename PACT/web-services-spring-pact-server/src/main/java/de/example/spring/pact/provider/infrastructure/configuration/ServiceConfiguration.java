package de.example.spring.pact.provider.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import de.example.spring.pact.provider.domain.service.CarServiceImpl;

@Configuration
public class ServiceConfiguration {
	
	@Bean
	public CarServiceImpl carServiceImpl() {
		return new CarServiceImpl();
	}
}
