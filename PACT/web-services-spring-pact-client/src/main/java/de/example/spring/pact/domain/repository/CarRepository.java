package de.example.spring.pact.domain.repository;

import java.util.List;

import de.example.spring.pact.domain.entity.Car;

public interface CarRepository {

	List<Car> findAll();
}
