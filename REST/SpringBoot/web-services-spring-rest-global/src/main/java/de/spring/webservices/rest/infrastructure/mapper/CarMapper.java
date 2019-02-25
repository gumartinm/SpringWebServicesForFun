package de.spring.webservices.rest.infrastructure.mapper;

import java.util.List;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import de.spring.webservices.rest.domain.Car;
import de.spring.webservices.rest.infrastructure.dto.CarDto;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CarMapper {
	CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);

	CarDto mapToCarDto(Car car);

	Car mapToCar(CarDto carDto);

	List<CarDto> mapToCarDtos(List<Car> cars);

	List<Car> mapToCars(List<CarDto> carDtos);
}
