package de.spring.webservices.rest.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import de.spring.webservices.infrastructure.mapper.CarMapper;
import de.spring.webservices.rest.repository.CarRepository;
import de.spring.webservices.rest.repository.impl.CarRestTemplateRepository;

@Configuration
public class RepositoryConfiguration {
	@Value("${app.url.base}")
	private String uriHost;

	@Bean
	public CarRepository carRepository(RestTemplate restTemplate, CarMapper carMapper) {
		return new CarRestTemplateRepository(uriHost, restTemplate, carMapper);
	}

	@Bean
	public RestTemplate schemaRegistryConfigurationService() {
		return new RestTemplateBuilder().build();
	}
}
