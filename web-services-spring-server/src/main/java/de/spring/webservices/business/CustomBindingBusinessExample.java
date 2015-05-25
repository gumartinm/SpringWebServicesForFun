package de.spring.webservices.business;

import org.springframework.stereotype.Service;

import de.spring.webservices.auto.CustomBindingExampleRequest;
import de.spring.webservices.auto.CustomBindingExampleResponse;
import de.spring.webservices.operations.Operations;


@Service("customBindingBusinessExample")
public class CustomBindingBusinessExample implements
	Operations.RequestResponse<CustomBindingExampleResponse, CustomBindingExampleRequest> {

	
	@Override
    public CustomBindingExampleResponse requestResponse(
    		final CustomBindingExampleRequest request) {
        final CustomBindingExampleResponse response = new CustomBindingExampleResponse();

        response.setData("CUSTOM BINDING SNAKE EYES AND " + request.getData());

        return response;
    }

}
