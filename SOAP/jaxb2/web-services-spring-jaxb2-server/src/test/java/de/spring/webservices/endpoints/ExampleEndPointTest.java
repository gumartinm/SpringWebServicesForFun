package de.spring.webservices.endpoints;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

import de.spring.webservices.operations.Operations;
import de.spring.webservices.server.auto.CustomBindingExampleRequest;
import de.spring.webservices.server.auto.CustomBindingExampleResponse;
import de.spring.webservices.server.auto.ExampleRequest;
import de.spring.webservices.services.ExampleService;


public class ExampleEndPointTest {
	
	private ExampleService exampleService;
	
	private Operations.RequestResponse
	<CustomBindingExampleResponse, CustomBindingExampleRequest> customBindingExampleService;
	
	private ExampleEndPoint exampleEndPoint;

	@Before
    public void init() {
		exampleService = mock(ExampleService.class);
		customBindingExampleService = mock(Operations.RequestResponse.class);
		exampleEndPoint = new ExampleEndPoint(customBindingExampleService, exampleService);
	}
	
	@Test
	public void givenExampleRequestThenInvokeExampleService() {
		ExampleRequest request = new ExampleRequest();
		request.setData("SCARLETT");
        
		exampleEndPoint.exampleResponse(request, null, null);
		
		verify(exampleService).doResponse(request);
	}
	
	@Test
	public void givenCustomBindingExampleRequestThenInvokeCustomBindingExampleService() {
		CustomBindingExampleRequest request = new CustomBindingExampleRequest();
		request.setData("SCARLETT");
        
		exampleEndPoint.cuntomBindingExampleResponse(request, null, null);
		
		verify(customBindingExampleService).requestResponse(request);
	}

}
