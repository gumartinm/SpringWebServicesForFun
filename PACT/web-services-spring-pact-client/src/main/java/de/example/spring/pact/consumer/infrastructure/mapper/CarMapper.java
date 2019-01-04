package de.example.spring.pact.consumer.infrastructure.mapper;

import java.util.List;
import java.util.stream.Collectors;

import de.example.spring.pact.consumer.domain.entity.Car;
import de.example.spring.pact.consumer.infrastructure.repository.dto.CarDto;

public class CarMapper {

	public Car mapToCar(CarDto carDto) {
		return new Car.Builder()
				.withBrand(carDto.getBrand())
				.withEngine(carDto.getEngine())
				.build();
	}
	
	public List<Car> mapToCars(List<CarDto> carDtos) {
		return carDtos
				.stream()
				.map(this::mapToCar)
				.collect(Collectors.toList());
	}
}
