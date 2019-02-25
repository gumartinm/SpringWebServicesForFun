package de.spring.webservices.rest.client.service.impl;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import de.spring.webservices.domain.Car;
import de.spring.webservices.infrastructure.dto.CarDto;
import de.spring.webservices.infrastructure.mapper.CarMapper;
import de.spring.webservices.rest.client.service.CarClientService;

public class CarClientServiceImpl implements CarClientService {
	private static final String ALL_CARS = "/api/cars/";
	private static final String ONE_CAR = "/api/cars/:id";
	private static final Logger LOGGER = LoggerFactory.getLogger(CarClientServiceImpl.class);

	private final String apiCarsUrl;
	private final String apiCarUrl;
	private final RestTemplate restTemplate;
	private final CarMapper carMapper;
	
	public CarClientServiceImpl(String uriHost, RestTemplate restTemplate, CarMapper carMapper) {
		this.apiCarsUrl = uriHost.concat(ALL_CARS);
		this.apiCarUrl = uriHost.concat(ONE_CAR);
		this.restTemplate = restTemplate;
		this.carMapper = carMapper;
	}

	
    @Override
	public List<Car> doGetCars() {				
		ResponseEntity<CarDto[]> responseEntity = restTemplate.getForEntity(apiCarsUrl, CarDto[].class);
		
		List<CarDto> carDtos = Arrays.asList(responseEntity.getBody());
		return carMapper.mapToCars(carDtos);
	}
	
    @Override
	public Car doGetCar(long id) {				
		ResponseEntity<CarDto> responseEntity = restTemplate.getForEntity(
		        apiCarUrl.replace(":id", String.valueOf(id)), CarDto.class);
		
		return carMapper.mapToCar(responseEntity.getBody());
	}
	
    @Override
	public Car doNewCar(Car car) {
		CarDto carDto = CarDto.builder()
				.id(car.getId())
				.content(car.getContent())
				.build();
		ResponseEntity<CarDto> responseEntity = restTemplate.postForEntity(apiCarsUrl, carDto, CarDto.class);
		URI newCarLocation = responseEntity.getHeaders().getLocation();
		LOGGER.info("new car location: " + newCarLocation.getPath());
			
		return carMapper.mapToCar(responseEntity.getBody());
	}
}
