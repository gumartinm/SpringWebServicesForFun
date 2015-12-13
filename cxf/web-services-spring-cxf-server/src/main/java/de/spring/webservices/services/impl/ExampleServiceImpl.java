package de.spring.webservices.services.impl;

import org.springframework.stereotype.Service;

import de.spring.webservices.auto.ExampleRequest;
import de.spring.webservices.auto.ExampleResponse;
import de.spring.webservices.services.ExampleService;


@Service("exampleService")
public class ExampleServiceImpl implements ExampleService {
	
	@Override
    public ExampleResponse doResponse(final ExampleRequest request) {
		
		ExampleResponse response = new ExampleResponse();

        response.setData("SNAKE EYES AND " + request.getData());

        return response;
    }

}
