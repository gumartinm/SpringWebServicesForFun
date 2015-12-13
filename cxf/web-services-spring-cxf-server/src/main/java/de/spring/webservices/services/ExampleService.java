package de.spring.webservices.services;

import de.spring.webservices.auto.ExampleRequest;
import de.spring.webservices.auto.ExampleResponse;


public interface ExampleService {
	
	public ExampleResponse doResponse(ExampleRequest request);

}
