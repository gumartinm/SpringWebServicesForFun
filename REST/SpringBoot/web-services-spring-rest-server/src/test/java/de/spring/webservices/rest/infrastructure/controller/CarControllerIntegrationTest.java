package de.spring.webservices.rest.infrastructure.controller;

import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import de.spring.webservices.domain.Car;
import de.spring.webservices.infrastructure.dto.CarDto;
import de.spring.webservices.infrastructure.mapper.CarMapper;

public class CarControllerIntegrationTest {
	private static final String TEMPLATE = "Car: %s";

	private CarController carController;
	private MockMvc mockMvc;
	private CarMapper carMapper;
	
    @Before
	public void setup() {
		carMapper = mock(CarMapper.class);
		carController = new CarController(carMapper);
        mockMvc = MockMvcBuilders
		        .standaloneSetup(carController)
                .build();
    }

	@Test
	public void whenFindAllThenRetrieveListOfJsons() throws Exception {
		ArgumentCaptor<List<Car>> carsArg = ArgumentCaptor.forClass(List.class);
		List<CarDto> carDtos = new ArrayList<>();
		carDtos.add(CarDto.builder().id(1L).content(String.format(TEMPLATE, 1)).build());
		carDtos.add(CarDto.builder().id(2L).content(String.format(TEMPLATE, 2)).build());
		carDtos.add(CarDto.builder().id(3L).content(String.format(TEMPLATE, 3)).build());
		given(carMapper.mapToCarDtos(carsArg.capture())).willReturn(carDtos);
		
		mockMvc.perform(get("/api/cars/")
				.accept(MediaType.APPLICATION_JSON_UTF8))
		
		.andExpect(status().isOk())
		.andExpect(jsonPath("$[0].id", any(Integer.class)))
		.andExpect(jsonPath("$[0].content", is("Car: 1")))
		.andExpect(jsonPath("$[1].content", is("Car: 2")))
		.andExpect(jsonPath("$[1].id", any(Integer.class)))
		.andExpect(jsonPath("$[2].content", is("Car: 3")))
		.andExpect(jsonPath("$[2].id", any(Integer.class)))
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
		
		List<Car> cars = carsArg.getValue();
		verify(carMapper, times(1)).mapToCarDtos(cars);
	}
	
	@Test
	public void whenFindOneThenRetrieveJson() throws Exception {
		ArgumentCaptor<Car> carArg = ArgumentCaptor.forClass(Car.class);
		CarDto carDto = CarDto.builder().id(1L).content(String.format(TEMPLATE, 1)).build();
		given(carMapper.mapToCarDto(carArg.capture())).willReturn(carDto);

		mockMvc.perform(get("/api/cars/{id}", 1L)
				.accept(MediaType.APPLICATION_JSON_UTF8))
	
		.andExpect(status().isOk())
		.andExpect(jsonPath("id", any(Integer.class)))
		.andExpect(jsonPath("content", is("Car: 1")))
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));

		Car car = carArg.getValue();
		verify(carMapper, times(1)).mapToCarDto(car);
	}
	
	@Test
	public void whenCreateThenRetrieveJson() throws Exception {
		ArgumentCaptor<Car> carArg = ArgumentCaptor.forClass(Car.class);
		CarDto carDto = CarDto.builder().id(1L).content(String.format(TEMPLATE, 1)).build();
		given(carMapper.mapToCarDto(carArg.capture())).willReturn(carDto);

		mockMvc.perform(post("/api/cars/")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
		        .content(carDtoAsJson())
				.accept(MediaType.APPLICATION_JSON_UTF8))
		
		.andExpect(status().isCreated())
		.andExpect(jsonPath("id", any(Integer.class)))
		        .andExpect(jsonPath("content", is("Car: 1")))
		        .andExpect(header().string(HttpHeaders.LOCATION, "/api/cars/1"))
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));

		Car car = carArg.getValue();
		verify(carMapper, times(1)).mapToCarDto(car);
	}

	private String carDtoAsJson() {
		return "{\"id\":2, \"content\": \"nothing\"}";
	}
}
