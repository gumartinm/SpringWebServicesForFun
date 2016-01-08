package de.spring.webservices.rest.client.service;

import java.util.List;

import de.spring.webservices.domain.Car;

public interface CarClientService {

	public List<Car> doGetCars();
	
	public Car doGetCar(long id);
	
	public Car doNewCar(Car car);
}
