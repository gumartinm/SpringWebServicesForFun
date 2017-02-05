package de.spring.webservices.rest.client.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.spring.webservices.domain.Car;
import de.spring.webservices.rest.business.service.BusinessService;

/** WHEN USING @RunWith SPRING SEARCHES FOR YOUR main Aplication AND RUNS IT!!!!! **/
@RunWith(SpringRunner.class)
@RestClientTest({ CarClientService.class })
@PropertySource("classpath:application.yml")
public class CarClientServiceIntegrationTest {
	
	@Value("${app.url.base}${app.url.cars}")
	private String apiCarsUrl;
	
	@Value("${app.url.base}${app.url.car}")
	private String apiCarUrl;
    
		
	// WARNING!!!! @RunWith runs searches for the main Application in class path and runs it!!!
	// Because in my main Application I am injecting the BusinessSerivce I have to mock it in order to make
	// this test work even when BusinessService is not related to CarclientService :O
	@MockBean
	private BusinessService businessService;
	
    @Inject
	private CarClientService carClientService;
    
    @Inject
    private ObjectMapper jsonObjectMapperFactory;

    @Inject
    private MockRestServiceServer mockServer;

	@Test
	public void whenGetAllCarsThenRetrieveRequestedCars() throws JsonProcessingException {
		Car expectedOne = new Car(66L, "test");
		List<Car> expected = new ArrayList<>();
		expected.add(expectedOne);
		
		mockServer.expect(requestTo(apiCarsUrl))
					.andExpect(method(HttpMethod.GET))
					.andRespond(withSuccess(asJsonString(expected), MediaType.APPLICATION_JSON_UTF8));

		List<Car> cars = carClientService.doGetCars();

		//mockServer.verify();
		
		assertEquals(1, cars.size());
		assertEquals(expectedOne, cars.get(0));
	}
	
	@Test
	public void whenGetCarByIdThenRetrieveRequestedCar() throws JsonProcessingException {
		Long id = 66L;
		Car expected = new Car(66L, "test");
		
		mockServer.expect(requestTo(apiCarUrl.replace(":id", String.valueOf(id))))
					.andExpect(method(HttpMethod.GET))
					.andRespond(withSuccess(asJsonString(expected), MediaType.APPLICATION_JSON_UTF8));

		Car car = carClientService.doGetCar(id);

		//mockServer.verify();
		
		assertNotNull(car);
		assertEquals(expected, car);
	}
	
	@Test
	public void whenCreateNewCarThenRetrieveCreatedCar() throws JsonProcessingException {
		Long expectedId = 66L;
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.LOCATION, "/api/cars/" + String.valueOf(expectedId));
		Car expected = new Car(expectedId, "test");
		
		mockServer.expect(requestTo(apiCarsUrl))
					.andExpect(method(HttpMethod.POST))
					.andExpect(content()
							.string(asJsonString(expected)))
					.andRespond(withSuccess(asJsonString(expected), MediaType.APPLICATION_JSON_UTF8)
							.headers(headers));

		Car car = carClientService.doNewCar(expected);

		//mockServer.verify();
		
		assertNotNull(car);
		assertEquals(expected, car);
	}
	
	private String asJsonString(final Object obj) throws JsonProcessingException {
		return jsonObjectMapperFactory.writeValueAsString(obj);
	}
}
