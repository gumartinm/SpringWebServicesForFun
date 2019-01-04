package de.example.spring.pact.infrastructure.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import de.example.spring.pact.domain.entity.Car;
import de.example.spring.pact.domain.service.CarServiceImpl;

@RestController("/cars/")
public class CarController {
	private final CarServiceImpl carServiceImpl;
	
	@Inject
	public CarController(CarServiceImpl carServiceImpl) {
		this.carServiceImpl = carServiceImpl;
	}

	@GetMapping
	@ResponseStatus(value = HttpStatus.OK)
	public List<Car> findAll() {
		return carServiceImpl.findAll();
	}
}
