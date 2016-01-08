package de.spring.webservices.services;

import de.spring.webservices.server.auto.ExampleRequest;
import de.spring.webservices.server.auto.ExampleResponse;


public interface ExampleService {
	
	public ExampleResponse doResponse(ExampleRequest request);

}
