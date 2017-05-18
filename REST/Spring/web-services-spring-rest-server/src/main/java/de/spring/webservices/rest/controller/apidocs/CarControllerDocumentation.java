package de.spring.webservices.rest.controller.apidocs;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import de.spring.webservices.domain.Car;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ResponseHeader;

public interface CarControllerDocumentation {
	
	@ApiOperation(value = "Get all available cars", nickname = "getAllCars", responseContainer="List", response = Car.class)
    @ApiResponses({
        @ApiResponse(code =  404, message ="Specific getCars not found"),
        @ApiResponse(code =  400, message ="Specific getCars invalid input")
    })
	List<Car> cars();

	@ApiOperation(value = "Get one car", nickname = "getOneCar", response = Car.class)
    @ApiResponses({
        @ApiResponse(code =  404, message ="Specific getCar not found"),
        @ApiResponse(code =  400, message ="Specific getCar invalid input")
    })
    Car car(String specialHeader,
    		@ApiParam(name = "id", value = "Car id", required = true) long id,
    		Map<String, String> params,
    		String[] wheelParams);
	
	@ApiOperation(code =  201, value = "Create one new car", nickname = "createNewCar")
    @ApiResponses({
    	@ApiResponse(code =  201, message ="Specific createCar with header",
    			responseHeaders = { @ResponseHeader(name = HttpHeaders.LOCATION) }, response = Car.class),
        @ApiResponse(code =  404, message ="Specific createCar not found"),
        @ApiResponse(code =  400, message ="Specific createCar invalid input")
    })
	ResponseEntity<Car> create(Car car);
}
