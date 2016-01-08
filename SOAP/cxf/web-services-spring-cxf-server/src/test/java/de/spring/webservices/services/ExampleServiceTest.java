package de.spring.webservices.services;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.spring.webservices.server.auto.ExampleRequest;
import de.spring.webservices.server.auto.ExampleResponse;
import de.spring.webservices.services.impl.ExampleServiceImpl;

public class ExampleServiceTest {

    private ExampleService exampleService;
	
	@Before
    public void init() {
		exampleService = new ExampleServiceImpl();
	}

	@Test
	public void givenExampleRequestThenReturnExampleResponse() {
		ExampleRequest request = new ExampleRequest();
		request.setData("SCARLETT");
		ExampleResponse expected = new ExampleResponse();
		expected.setData("SNAKE EYES AND " + request.getData());
		
        ExampleResponse actual = exampleService.doResponse(request);
        
		Assert.assertEquals(expected.getData(), actual.getData());
	}

}
