package de.spring.webservices.rest.infrastructure.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import javax.inject.Inject;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import de.spring.webservices.domain.Car;
import de.spring.webservices.infrastructure.dto.CarDto;
import de.spring.webservices.infrastructure.mapper.CarMapper;
import de.spring.webservices.rest.infrastructure.controller.apidocs.CarControllerDocumentation;

@RestController
@RequestMapping("/api/cars/")
public class CarController implements CarControllerDocumentation {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CarController.class);
    private static final String TEMPLATE = "Car: %s";
    
    private final AtomicLong counter = new AtomicLong();
	private final CarMapper carMapper;

	@Inject
	public CarController(CarMapper carMapper) {
		this.carMapper = carMapper;
	}

	@GetMapping(produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    @ResponseStatus(HttpStatus.OK)
	@Override
	public List<CarDto> findAll() {
		List<Car> cars = new ArrayList<>();
		cars.add(Car.builder().id(counter.incrementAndGet()).content(String.format(TEMPLATE, 1)).build());
		cars.add(Car.builder().id(counter.incrementAndGet()).content(String.format(TEMPLATE, 2)).build());
		cars.add(Car.builder().id(counter.incrementAndGet()).content(String.format(TEMPLATE, 3)).build());

		return carMapper.mapToCarDtos(cars);
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@Override
	public CarDto findOne(@RequestHeader(value = "MY_HEADER", required = false) String specialHeader,
    		@PathVariable("id") long id,
    		@RequestParam Map<String, String> params,
    		@RequestParam(value = "wheel", required = false) String[] wheelParams) {
		
		MDC.put("UUID", "some-random-uuid");
    	    	
    	if (specialHeader != null) {
    		LOGGER.info("SPECIAL HEADER: " + specialHeader);
    	}
    	 
    	if (params.get("mirror") != null) {
    		LOGGER.info("MIRROR: " + params.get("mirror"));	
    	}
    	
    	if (params.get("window") != null) {
    		LOGGER.info("WINDOW: " + params.get("window"));
    	}
    	
    	if (wheelParams != null) {
    		for(String wheel : wheelParams) {
    			LOGGER.info(wheel);
    		}
    	}
    	
        try {
			Thread.sleep(10000);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }


		Car car = Car.builder().id(counter.incrementAndGet()).content(String.format(TEMPLATE, id)).build();
		return carMapper.mapToCarDto(car);
    }
    
    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	@Override
	public ResponseEntity<CarDto> create(@RequestBody @Valid CarDto carDto) {
    	long count = counter.incrementAndGet();
    	HttpHeaders headers = new HttpHeaders();
    	headers.add(HttpHeaders.LOCATION, "/api/cars/" + count);
    	
		Car newCar = Car.builder().id(counter.incrementAndGet()).content(String.format(TEMPLATE, count)).build();
		CarDto newCarDto = carMapper.mapToCarDto(newCar);
		return new ResponseEntity<>(newCarDto, headers, HttpStatus.CREATED);
    }

}
