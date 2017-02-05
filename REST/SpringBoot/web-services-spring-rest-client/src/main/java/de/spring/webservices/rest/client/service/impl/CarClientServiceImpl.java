package de.spring.webservices.rest.client.service.impl;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import de.spring.webservices.domain.Car;
import de.spring.webservices.rest.client.service.CarClientService;

@Named("carClientService")
public class CarClientServiceImpl implements CarClientService {
	private static final Logger LOGGER = LoggerFactory.getLogger(CarClientServiceImpl.class);

	private final String apiCarsUrl;
	private final String apiCarUrl;
	private final RestTemplate restTemplate;
	
    @Inject
	public CarClientServiceImpl(@Value("${app.url.base}${app.url.cars}") String apiCarsUrl,
			@Value("${app.url.base}${app.url.car}") String apiCarUrl, RestTemplateBuilder restTemplateBuilder) {
		this.apiCarsUrl = apiCarsUrl;
		this.apiCarUrl = apiCarUrl;
		this.restTemplate = restTemplateBuilder.build();
	}

	
    @Override
	public List<Car> doGetCars() {				
		ResponseEntity<Car[]> responseEntity = restTemplate.getForEntity(apiCarsUrl, Car[].class);
		
		return Arrays.asList(responseEntity.getBody());
	}
	
    @Override
	public Car doGetCar(long id) {				
		ResponseEntity<Car> responseEntity = restTemplate.getForEntity(
				apiCarUrl.replace(":id", String.valueOf(id)), Car.class);
		
		return responseEntity.getBody();
	}
	
    @Override
	public Car doNewCar(Car car) {		
		ResponseEntity<Car> responseEntity = restTemplate.postForEntity(apiCarsUrl, car, Car.class);
		URI newCarLocation = responseEntity.getHeaders().getLocation();
		LOGGER.info("new car location: " + newCarLocation.getPath());
			
		return responseEntity.getBody();
	}
}
