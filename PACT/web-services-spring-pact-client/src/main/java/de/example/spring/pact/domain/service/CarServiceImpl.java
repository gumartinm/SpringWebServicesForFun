package de.example.spring.pact.domain.service;

import java.util.List;

import de.example.spring.pact.domain.entity.Car;
import de.example.spring.pact.infrastructure.repository.CarRepository;

public class CarServiceImpl {
	private final CarRepository carRepository;
	
	public CarServiceImpl(CarRepository carRepository) {
		this.carRepository = carRepository;
	}

	public List<Car> findAll() {
		return carRepository.findAll();
	}
}
