package de.example.spring.pact.infrastructure.repository;

import java.util.List;

import de.example.spring.pact.domain.entity.Car;
import de.example.spring.pact.domain.repository.CarRepository;
import de.example.spring.pact.infrastructure.feign.client.CarFeignClient;

public class CarRepositoryImpl implements CarRepository {
	private final CarFeignClient carFeignClient;
	
	public CarRepositoryImpl(CarFeignClient carFeignClient) {
		this.carFeignClient = carFeignClient;
	}

	@Override
	public List<Car> findAll() {
		return carFeignClient.findAll();
	}
}
