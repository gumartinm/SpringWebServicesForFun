package de.spring.webservices.rest.business.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.spring.webservices.domain.Car;
import de.spring.webservices.rest.business.service.BusinessService;
import de.spring.webservices.rest.client.service.CarClientService;

@Service("businessService")
public class BusinessServiceImpl implements BusinessService {
	private static final Logger LOGGER = LoggerFactory.getLogger(BusinessServiceImpl.class);

	private final CarClientService carClientService;

	@Autowired
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
		Car newCar = new Car(666L, "just_a_test");
		
		Car car = carClientService.doNewCar(newCar);
		LOGGER.info("New car");
		LOGGER.info("car: " + car.getId());
		LOGGER.info(car.getContent());
	}
}
