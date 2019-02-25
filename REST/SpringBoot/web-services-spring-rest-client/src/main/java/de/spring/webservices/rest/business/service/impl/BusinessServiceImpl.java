package de.spring.webservices.rest.business.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.spring.webservices.domain.Car;
import de.spring.webservices.rest.business.service.BusinessService;
import de.spring.webservices.rest.repository.CarRepository;

public class BusinessServiceImpl implements BusinessService {
	private static final Logger LOGGER = LoggerFactory.getLogger(BusinessServiceImpl.class);

	private final CarRepository carRepository;

	public BusinessServiceImpl(CarRepository carClientService) {
		this.carRepository = carClientService;
	}
	
	
	@Override
	public void doSomethingWithCars() {
		List<Car> cars = carRepository.findAll();
		LOGGER.info("Retrieved cars");
		for (Car car : cars) {
			LOGGER.info("car: " + car.getId());
			LOGGER.info(car.getContent());
		}
	}
	
	@Override
	public void doSomethingWithCar(long id) {		
		Car car = carRepository.findOne(id);
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
		
		Car car = carRepository.create(newCar);
		LOGGER.info("New car");
		LOGGER.info("car: " + car.getId());
		LOGGER.info(car.getContent());
	}
}
