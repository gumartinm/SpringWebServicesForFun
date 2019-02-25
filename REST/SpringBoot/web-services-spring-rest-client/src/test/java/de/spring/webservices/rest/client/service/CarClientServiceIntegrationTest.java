package de.spring.webservices.rest.client.service;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.tomakehurst.wiremock.common.ClasspathFileSource;
import com.github.tomakehurst.wiremock.junit.WireMockClassRule;

import de.spring.webservices.domain.Car;
import de.spring.webservices.infrastructure.dto.CarDto;
import de.spring.webservices.infrastructure.mapper.CarMapper;
import de.spring.webservices.rest.client.service.impl.CarClientServiceImpl;

public class CarClientServiceIntegrationTest {
	
	@ClassRule
	public static WireMockClassRule wireMockRule = new WireMockClassRule(
	        options().dynamicPort().fileSource(new ClasspathFileSource("wiremock")));

	private RestTemplate restTemplate;
	
	private CarClientService carClientService;
	private CarMapper carMapper;

	@Before
	public void before() {
		restTemplate = new RestTemplate();
		carMapper = CarMapper.INSTANCE;
		String uriHost = "http://localhost:" + wireMockRule.port();
		carClientService = new CarClientServiceImpl(uriHost, restTemplate, carMapper);
	}

	@Test
	public void whenGetAllCarsThenRetrieveRequestedCars() throws JsonProcessingException {
		CarDto expectedOne = CarDto.builder().id(66L).content("test").build();
		List<CarDto> expected = new ArrayList<>();
		expected.add(expectedOne);

		List<Car> cars = carClientService.doGetCars();
		
		assertEquals(1, cars.size());
		assertEquals(expectedOne.getContent(), cars.get(0).getContent());
		assertEquals(expectedOne.getId(), cars.get(0).getId());
	}
	
	@Test
	public void whenGetCarByIdThenRetrieveRequestedCar() throws JsonProcessingException {
		Long id = 66L;
		CarDto expected = CarDto.builder().id(id).content("test").build();

		Car car = carClientService.doGetCar(id);

		assertNotNull(car);
		assertEquals(expected.getContent(), car.getContent());
		assertEquals(expected.getId(), car.getId());
	}

	@Test
	public void whenCreateNewCarThenRetrieveCreatedCar() throws JsonProcessingException {
		Car expected = Car.builder().id(66L).content("test").build();

		Car car = carClientService.doNewCar(expected);

		assertNotNull(car);
		assertEquals(expected.getContent(), car.getContent());
		assertEquals(expected.getId(), car.getId());
	}
}
