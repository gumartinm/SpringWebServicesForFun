package de.example.spring.pact.provider.infrastructure.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import de.example.spring.pact.provider.domain.entity.Car;
import de.example.spring.pact.provider.domain.service.CarServiceImpl;

@RestController("/cars/")
public class CarController {
	private final CarServiceImpl carServiceImpl;
	
	@Inject
	public CarController(CarServiceImpl carServiceImpl) {
		this.carServiceImpl = carServiceImpl;
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public List<Car> findAll() {
		return carServiceImpl.findAll();
	}
}
