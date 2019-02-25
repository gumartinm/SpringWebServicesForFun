package de.spring.webservices.rest.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import de.spring.webservices.rest.business.service.BusinessService;
import de.spring.webservices.rest.business.service.impl.BusinessServiceImpl;
import de.spring.webservices.rest.repository.CarRepository;

@Configuration
public class ServiceConfiguration {

	@Bean
	public BusinessService businessServiceImpl(CarRepository carRepository) {
		return new BusinessServiceImpl(carRepository);
	}
}
