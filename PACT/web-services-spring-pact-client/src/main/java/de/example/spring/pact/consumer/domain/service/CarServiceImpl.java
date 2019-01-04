package de.example.spring.pact.consumer.domain.service;

import java.util.List;

import de.example.spring.pact.consumer.domain.entity.Car;
import de.example.spring.pact.consumer.domain.repository.CarRepository;

public class CarServiceImpl {
	private final CarRepository carRepository;
	
	public CarServiceImpl(CarRepository carRepository) {
		this.carRepository = carRepository;
	}

	public List<Car> findAll() {
		return carRepository.findAll();
	}
}
