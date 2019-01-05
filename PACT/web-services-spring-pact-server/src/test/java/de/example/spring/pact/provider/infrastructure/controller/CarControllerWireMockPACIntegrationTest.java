package de.example.spring.pact.provider.infrastructure.controller;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.RestPactRunner;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactBroker;
import au.com.dius.pact.provider.junit.target.TestTarget;
import au.com.dius.pact.provider.spring.target.MockMvcTarget;
import de.example.spring.pact.provider.domain.entity.Car;
import de.example.spring.pact.provider.domain.service.CarServiceImpl;

@Ignore
@RunWith(RestPactRunner.class)
@Provider("cars_wiremockpact_provider")
@PactBroker(host = "${pactbroker.host:localhost}", port = "${pactbroker.port:80}")
public class CarControllerWireMockPACIntegrationTest {

	private CarController carController;

	private CarServiceImpl carServiceImpl;

	@TestTarget
	public final MockMvcTarget mockMvc = new MockMvcTarget();

	@Before
	public void setUpClass() {
		carServiceImpl = mock(CarServiceImpl.class);
		carController = new CarController(carServiceImpl);
		mockMvc.setControllers(carController);
	}

    @State("test state")
	@Test
	public void shouldFindAll() throws Exception {
		Car car = new Car.CarBuilder().withBrand("Ford").withEngine("Diesel").build();
		List<Car> cars = Collections.singletonList(car);

		mockMvc.setRunTimes(1);
		given(carServiceImpl.findAll()).willReturn(cars);
	}
}
