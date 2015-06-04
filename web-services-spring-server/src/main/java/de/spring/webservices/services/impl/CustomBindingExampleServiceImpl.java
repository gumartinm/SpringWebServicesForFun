package de.spring.webservices.services.impl;

import org.springframework.stereotype.Service;

import de.spring.webservices.auto.CustomBindingExampleRequest;
import de.spring.webservices.auto.CustomBindingExampleResponse;
import de.spring.webservices.auto.ParentEnumType;
import de.spring.webservices.operations.Operations;


@Service("customBindingExampleService")
public class CustomBindingExampleServiceImpl implements
	Operations.RequestResponse<CustomBindingExampleResponse, CustomBindingExampleRequest> {

	
	@Override
    public CustomBindingExampleResponse requestResponse(final CustomBindingExampleRequest request) {
		
        CustomBindingExampleResponse response = new CustomBindingExampleResponse();

        response.setData("CUSTOM BINDING SNAKE EYES AND " + request.getData());
        response.setParentEnum(ParentEnumType.FIRST);

        return response;
    }

}
