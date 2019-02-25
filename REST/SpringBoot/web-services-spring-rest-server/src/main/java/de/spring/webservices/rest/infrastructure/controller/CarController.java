package de.spring.webservices.rest.infrastructure.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

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

import de.spring.webservices.infrastructure.dto.CarDto;
import de.spring.webservices.rest.infrastructure.controller.apidocs.CarControllerDocumentation;

@RestController
@RequestMapping("/api/cars/")
public class CarController implements CarControllerDocumentation {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CarController.class);
    private static final String TEMPLATE = "Car: %s";
    
    @NotNull
    private final AtomicLong counter = new AtomicLong();

	@GetMapping(produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
    @ResponseStatus(HttpStatus.OK)
	@Override
	public List<CarDto> cars() {
		final List<CarDto> carDtos = new ArrayList<>();
		carDtos.add(CarDto.builder().id(counter.incrementAndGet()).content(String.format(TEMPLATE, 1)).build());
		carDtos.add(CarDto.builder().id(counter.incrementAndGet()).content(String.format(TEMPLATE, 2)).build());
		carDtos.add(CarDto.builder().id(counter.incrementAndGet()).content(String.format(TEMPLATE, 3)).build());

		return carDtos;
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@Override
	public CarDto car(@RequestHeader(value = "MY_HEADER", required = false) String specialHeader,
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
            Thread.sleep(10000);                 //1000 milliseconds is one second.
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }


		return CarDto.builder().id(counter.incrementAndGet()).content(String.format(TEMPLATE, id)).build();
    }
    
    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	@Override
	public ResponseEntity<CarDto> create(@RequestBody @Valid CarDto carDto) {
    	long count = counter.incrementAndGet();
    	HttpHeaders headers = new HttpHeaders();
    	headers.add(HttpHeaders.LOCATION, "/api/cars/" + count);
    	
		return new ResponseEntity<>(
		        CarDto.builder().id(counter.incrementAndGet()).content(String.format(TEMPLATE, count)).build(), headers,
		        HttpStatus.CREATED);
    }

}
