package de.example.spring.pact.consumer.infrastructure.repository.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import de.example.spring.pact.consumer.domain.entity.Car;

public class CarDto extends Car {

	public CarDto(@JsonProperty("brand") String brand,
				  @JsonProperty("engine") String engine) {
		super(brand, engine);
	}
}
