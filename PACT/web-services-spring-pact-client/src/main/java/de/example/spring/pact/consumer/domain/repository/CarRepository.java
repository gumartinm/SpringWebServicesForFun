package de.example.spring.pact.consumer.domain.repository;

import java.util.List;

import de.example.spring.pact.consumer.domain.entity.Car;

public interface CarRepository {

	List<Car> findAll();
}
