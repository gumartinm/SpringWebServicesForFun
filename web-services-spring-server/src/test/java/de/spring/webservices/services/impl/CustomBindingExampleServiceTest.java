package de.spring.webservices.services.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.spring.webservices.auto.CustomBindingExampleRequest;
import de.spring.webservices.auto.CustomBindingExampleResponse;
import de.spring.webservices.operations.Operations;


public class CustomBindingExampleServiceTest {

	private Operations.RequestResponse
	<CustomBindingExampleResponse, CustomBindingExampleRequest>  customBindingExampleService;
	
	@Before
    public void init() {
		customBindingExampleService = new CustomBindingExampleServiceImpl();
	}
	
	@Test
	public void givenCustomBindingExampleRequestThenReturnCustomBindingExampleResponse() {
		CustomBindingExampleRequest request = new CustomBindingExampleRequest();
		request.setData("SCARLETT");
		CustomBindingExampleResponse expected = new CustomBindingExampleResponse();
		expected.setData("CUSTOM BINDING SNAKE EYES AND " + request.getData());
		
		CustomBindingExampleResponse actual = customBindingExampleService.requestResponse(request);
        
		Assert.assertEquals(expected.getData(), actual.getData());
	}
}
