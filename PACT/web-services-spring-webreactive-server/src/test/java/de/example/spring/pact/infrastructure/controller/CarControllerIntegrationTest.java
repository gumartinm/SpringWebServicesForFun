package de.example.spring.pact.infrastructure.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.reagroup.pact.provider.PactRunner;
import com.reagroup.pact.provider.ProviderState;

import au.com.dius.pact.provider.junit.loader.PactBroker;
import de.example.spring.pact.domain.entity.Car;
import de.example.spring.pact.domain.service.CarServiceImpl;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Collections;

@RunWith(PactRunner.class)
@PactBroker(host = "localhost", port = "80")
public class CarControllerIntegrationTest {

	@Autowired
	private CarController carController;

	private CarServiceImpl carServiceImpl;

	private MockMvc mockMvc;

	@Before
	public void setUpClass() {
		carServiceImpl = mock(CarServiceImpl.class);
		mockMvc = MockMvcBuilders.standaloneSetup(new CarController(carServiceImpl)).build();
	}

    @ProviderState("some_provider")
	@Test
	public CarController shouldFindAll() throws Exception {
		Car car = new Car.CarBuilder().withBrand("Ford").withEngine("Diesel").build();
		List<Car> cars = Collections.singletonList(car);
		given(carServiceImpl.findAll()).willReturn(cars);

		mockMvc
			.perform(get("/cars/"))
			.andExpect(jsonPath("$.[0].brand", is("Ford")))
			.andExpect(jsonPath("$.[0].engine", is("Diesel")))
			.andExpect(status().isOk());

		verify(carServiceImpl, times(1)).findAll();

		return carController;
	}
}
