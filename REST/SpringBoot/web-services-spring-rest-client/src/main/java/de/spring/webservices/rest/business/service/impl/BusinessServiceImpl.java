package de.spring.webservices.rest.business.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.spring.webservices.domain.Car;
import de.spring.webservices.rest.business.service.BusinessService;
import de.spring.webservices.rest.client.service.CarClientService;

@Named("businessService")
public class BusinessServiceImpl implements BusinessService {
	private static final Logger LOGGER = LoggerFactory.getLogger(BusinessServiceImpl.class);

	private final CarClientService carClientService;

    @Inject
	public BusinessServiceImpl(CarClientService carClientService) {
		this.carClientService = carClientService;
	}
	
	
	@Override
	public void doSomethingWithCars() {
		List<Car> cars = carClientService.doGetCars();
		LOGGER.info("Retrieved cars");
		for (Car car : cars) {
			LOGGER.info("car: " + car.getId());
			LOGGER.info(car.getContent());
		}
	}
	
	@Override
	public void doSomethingWithCar(long id) {		
		Car car = carClientService.doGetCar(id);
		LOGGER.info("Retrieved car");
		LOGGER.info("car: " + car.getId());
		LOGGER.info(car.getContent());
	}
	
	@Override
	public void createsNewCar() {
		Car newCar = Car.builder()
				.id(666L)
				.content("just_a_test")
				.build();
		
		Car car = carClientService.doNewCar(newCar);
		LOGGER.info("New car");
		LOGGER.info("car: " + car.getId());
		LOGGER.info(car.getContent());
	}
}
