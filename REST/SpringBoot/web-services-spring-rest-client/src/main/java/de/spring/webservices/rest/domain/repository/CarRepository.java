package de.spring.webservices.rest.domain.repository;

import java.util.List;

import de.spring.webservices.domain.Car;

public interface CarRepository {

	public List<Car> findAll();
	
	public Car findOne(long id);
	
	public Car create(Car car);
}
