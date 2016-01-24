package de.spring.webservices.rest.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import javax.ws.rs.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import de.spring.webservices.domain.Car;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ResponseHeader;

@RestController
@RequestMapping("/api/cars/")
public class CarController {
	private static final Logger LOGGER = LoggerFactory.getLogger(CarController.class);
    private static final String TEMPLATE = "Car: %s";
    
    private final AtomicLong counter = new AtomicLong();

	@ApiOperation(value = "Get all available cars", nickname = "getAllCars", responseContainer="List", response = Car.class)
    @ApiResponses({
        @ApiResponse(code =  404, message ="Specific getCars not found"),
        @ApiResponse(code =  400, message ="Specific getCars invalid input")
    })
    @RequestMapping(produces = { MediaType.APPLICATION_JSON_UTF8_VALUE }, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Car> cars() {
        final List<Car> cars = new ArrayList<>();
        cars.add(new Car(counter.incrementAndGet(), String.format(TEMPLATE, 1)));
        cars.add(new Car(counter.incrementAndGet(), String.format(TEMPLATE, 2)));
        cars.add(new Car(counter.incrementAndGet(), String.format(TEMPLATE, 3)));

        return cars;
    }

	@ApiOperation(value = "Get one car", nickname = "getOneCar", response = Car.class)
    @ApiResponses({
        @ApiResponse(code =  404, message ="Specific getCar not found"),
        @ApiResponse(code =  400, message ="Specific getCar invalid input")
    })
    @RequestMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Car car(@RequestHeader(value = "MY_HEADER", required = false) String specialHeader,
    		@ApiParam(name = "id", value = "Car id", required = true) @PathVariable("id") long id,
    		@RequestParam Map<String, String> params,
    		@RequestParam(value = "wheel", required = false) String[] wheelParams) {
    	    	
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


        return new Car(counter.incrementAndGet(), String.format(TEMPLATE, id));
    }
    
	@ApiOperation(code =  201, value = "Create one new car", nickname = "createNewCar")
    @ApiResponses({
    	@ApiResponse(code =  201, message ="Specific createCar with header",
    			responseHeaders = { @ResponseHeader(name = HttpHeaders.LOCATION) }, response = Car.class),
        @ApiResponse(code =  404, message ="Specific createCar not found"),
        @ApiResponse(code =  400, message ="Specific createCar invalid input")
    })
    @RequestMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
    		produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Car> create(@RequestBody Car car) {
    	long count = counter.incrementAndGet();
    	HttpHeaders headers = new HttpHeaders();
    	headers.add(HttpHeaders.LOCATION, "/api/cars/" + count);
    	
        return new ResponseEntity<>(new Car(count, String.format(TEMPLATE, count)), headers, HttpStatus.CREATED);
    }

}
