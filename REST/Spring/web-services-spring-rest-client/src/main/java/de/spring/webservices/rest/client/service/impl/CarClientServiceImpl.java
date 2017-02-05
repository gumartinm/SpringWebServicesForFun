package de.spring.webservices.rest.client.service.impl;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import de.spring.webservices.domain.Car;
import de.spring.webservices.rest.client.service.CarClientService;

@Service("carClientService")
public class CarClientServiceImpl implements CarClientService {
	private static final Logger LOGGER = LoggerFactory.getLogger(CarClientServiceImpl.class);

	private final String apiCarsUrl;
	private final String apiCarUrl;
	private final RestTemplate restTemplate;
	
    @Autowired
	public CarClientServiceImpl(@Value("${url.base}${url.cars}") String apiCarsUrl,
			@Value("${url.base}${url.car}") String apiCarUrl, RestTemplate restTemplate) {
		this.apiCarsUrl = apiCarsUrl;
		this.apiCarUrl = apiCarUrl;
		this.restTemplate = restTemplate;
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
