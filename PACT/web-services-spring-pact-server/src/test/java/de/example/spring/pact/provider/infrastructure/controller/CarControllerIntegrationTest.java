package de.example.spring.pact.provider.infrastructure.controller;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import de.example.spring.pact.provider.domain.entity.Car;
import de.example.spring.pact.provider.domain.service.CarServiceImpl;
import de.example.spring.pact.provider.infrastructure.controller.CarController;

public class CarControllerIntegrationTest {

	private CarController carController;

	private CarServiceImpl carServiceImpl;

	private MockMvc mockMvc;

	@Before
	public void setUpClass() {
		carServiceImpl = mock(CarServiceImpl.class);
		carController = new CarController(carServiceImpl);
		mockMvc = MockMvcBuilders.standaloneSetup(carController).build();
	}

	@Test
	public void shouldFindAll() throws Exception {
		Car car = new Car.Builder().withBrand("Ford").withEngine("Diesel").build();
		List<Car> cars = Collections.singletonList(car);
		given(carServiceImpl.findAll()).willReturn(cars);

		mockMvc
		.perform(get("/cars/"))
		.andExpect(jsonPath("$.[0].brand", is("Ford")))
		.andExpect(jsonPath("$.[0].engine", is("Diesel")))
		.andExpect(status().isOk());

		verify(carServiceImpl, times(1)).findAll();
	}
}
