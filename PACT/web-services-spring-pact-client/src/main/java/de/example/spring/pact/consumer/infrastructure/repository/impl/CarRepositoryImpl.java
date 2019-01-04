package de.example.spring.pact.consumer.infrastructure.repository.impl;

import java.util.List;

import de.example.spring.pact.consumer.domain.entity.Car;
import de.example.spring.pact.consumer.domain.repository.CarRepository;
import de.example.spring.pact.consumer.infrastructure.feign.client.CarFeignClient;
import de.example.spring.pact.consumer.infrastructure.mapper.CarMapper;

public class CarRepositoryImpl implements CarRepository {
	private final CarFeignClient carFeignClient;
	private final CarMapper carMapper;
	
	public CarRepositoryImpl(CarFeignClient carFeignClient,
							 CarMapper carMapper) {
		this.carFeignClient = carFeignClient;
		this.carMapper = carMapper;
	}

	@Override
	public List<Car> findAll() {
		return carMapper.mapToCars(carFeignClient.findAll());
	}
}
