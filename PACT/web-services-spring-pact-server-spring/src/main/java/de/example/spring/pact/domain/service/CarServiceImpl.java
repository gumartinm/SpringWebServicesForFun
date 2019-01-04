package de.example.spring.pact.domain.service;

import java.util.Collections;
import java.util.List;

import de.example.spring.pact.domain.entity.Car;

public class CarServiceImpl {
	
	public List<Car> findAll() {
		Car car = new Car.CarBuilder().withBrand("Ford").withEngine("Diesel").build();
		
		return Collections.singletonList(car);
	}
}
