package de.spring.webservices.rest.business.service;

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
import de.spring.webservices.rest.business.service.impl.BusinessServiceImpl;
import de.spring.webservices.rest.client.service.CarClientService;

public class BusinessServiceTest {

	private CarClientService carClientService;
	private BusinessService businessService;
	
    @Before
    public void createTest() {
    	carClientService = mock(CarClientService.class);
    	businessService = new BusinessServiceImpl(carClientService);	
    }
    
	@Test
	public void whenDoSomethingWithCarsThenInvokeDoGetCars() {
		Car expectedOne = Car.builder().id(66L).content("test").build();
		Car expectedTwo = Car.builder().id(99L).content("example").build();
		List<Car> expected = new ArrayList<>();
		expected.add(expectedOne);
		expected.add(expectedTwo);
		given(carClientService.doGetCars()).willReturn(expected);
		
		businessService.doSomethingWithCars();
		
		verify(carClientService, times(1)).doGetCars();
	}
    
	@Test
	public void whenDoSomethingWithOneCarhenInvokeDoGetCar() {
		Long id = 66L;
		Car expected = Car.builder().id(66L).content("test").build();
		
		given(carClientService.doGetCar(id)).willReturn(expected);
		
		businessService.doSomethingWithCar(id);
		
		verify(carClientService, times(1)).doGetCar(id);
	}
	
	@Test
	public void whenCreateNewCarThenCreateNewOne() {
		Car expected = Car.builder().id(66L).content("test").build();
		ArgumentCaptor<Car> argCar = ArgumentCaptor.forClass(Car.class);
		
		given(carClientService.doNewCar(argCar.capture())).willReturn(expected);
		
		businessService.createsNewCar();
		
		verify(carClientService, times(1)).doNewCar(argCar.getValue());
	}
}
