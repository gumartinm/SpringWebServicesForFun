package de.spring.webservices.rest.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import de.spring.webservices.rest.domain.repository.CarRepository;
import de.spring.webservices.rest.domain.service.BusinessService;
import de.spring.webservices.rest.domain.service.impl.BusinessServiceImpl;

@Configuration
public class ServiceConfiguration {

	@Bean
	public BusinessService businessServiceImpl(CarRepository carRepository) {
		return new BusinessServiceImpl(carRepository);
	}
}
