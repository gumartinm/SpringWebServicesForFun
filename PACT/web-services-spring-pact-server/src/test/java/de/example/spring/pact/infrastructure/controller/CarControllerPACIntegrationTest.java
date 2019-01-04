package de.example.spring.pact.infrastructure.controller;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.RestPactRunner;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactBroker;
import au.com.dius.pact.provider.junit.target.TestTarget;
import au.com.dius.pact.provider.spring.target.MockMvcTarget;
import de.example.spring.pact.domain.entity.Car;
import de.example.spring.pact.domain.service.CarServiceImpl;

@RunWith(RestPactRunner.class)
@Provider("some_provider")
@PactBroker(host = "${pactbroker.host:localhost}", port = "${pactbroker.port:80}")
public class CarControllerPACIntegrationTest {

	private CarController carController;

	private CarServiceImpl carServiceImpl;

	@TestTarget
	private MockMvcTarget mockMvc;

	@Before
	public void setUpClass() {
		carServiceImpl = mock(CarServiceImpl.class);
		carController = new CarController(carServiceImpl);
		mockMvc = new MockMvcTarget();
		mockMvc.setControllers(carController);
	}

    @State("state")
	@Test
	public void shouldFindAll() throws Exception {
		Car car = new Car.CarBuilder().withBrand("Ford").withEngine("Diesel").build();
		List<Car> cars = Collections.singletonList(car);
		given(carServiceImpl.findAll()).willReturn(cars);

		mockMvc.setRunTimes(1);

		verify(carServiceImpl, times(1)).findAll();
	}
}
