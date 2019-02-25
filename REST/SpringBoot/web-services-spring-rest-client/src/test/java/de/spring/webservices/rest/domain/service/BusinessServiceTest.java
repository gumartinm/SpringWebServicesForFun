package de.spring.webservices.rest.domain.service;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import de.spring.webservices.domain.Car;
import de.spring.webservices.rest.domain.repository.CarRepository;
import de.spring.webservices.rest.domain.service.impl.BusinessServiceImpl;

public class BusinessServiceTest {

	private CarRepository carRepository;
	private BusinessService businessService;
	
    @Before
    public void createTest() {
		carRepository = mock(CarRepository.class);
		businessService = new BusinessServiceImpl(carRepository);
    }
    
	@Test
	public void whenDoSomethingWithCarsThenInvokeDoGetCars() {
		Car expectedOne = Car.builder().id(66L).content("test").build();
		Car expectedTwo = Car.builder().id(99L).content("example").build();
		List<Car> expected = new ArrayList<>();
		expected.add(expectedOne);
		expected.add(expectedTwo);
		given(carRepository.findAll()).willReturn(expected);
		
		businessService.doSomethingWithCars();
		
		verify(carRepository, times(1)).findAll();
	}
    
	@Test
	public void whenDoSomethingWithOneCarhenInvokeDoGetCar() {
		Long id = 66L;
		Car expected = Car.builder().id(66L).content("test").build();
		
		given(carRepository.findOne(id)).willReturn(expected);
		
		businessService.doSomethingWithCar(id);
		
		verify(carRepository, times(1)).findOne(id);
	}
	
	@Test
	public void whenCreateNewCarThenCreateNewOne() {
		Car expected = Car.builder().id(66L).content("test").build();
		ArgumentCaptor<Car> argCar = ArgumentCaptor.forClass(Car.class);
		
		given(carRepository.create(argCar.capture())).willReturn(expected);
		
		businessService.createsNewCar();
		
		verify(carRepository, times(1)).create(argCar.getValue());
	}
}
