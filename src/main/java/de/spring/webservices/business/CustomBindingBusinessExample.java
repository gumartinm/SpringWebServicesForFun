package de.spring.webservices.business;

import de.spring.webservices.auto.CustomBindingExampleRequest;
import de.spring.webservices.auto.CustomBindingExampleResponse;
import de.spring.webservices.auto.ObjectFactory;
import de.spring.webservices.operations.Operations;

/**
 * Example of business class
 *
 */
public class CustomBindingBusinessExample
        implements Operations.RequestResponse<CustomBindingExampleResponse, CustomBindingExampleRequest> {

    @Override
    public CustomBindingExampleResponse requestResponse(final CustomBindingExampleRequest request) {
        final CustomBindingExampleResponse response = new ObjectFactory().createCustomBindingExampleResponse();

        response.setData("CUSTOM BINDING SNAKE EYES AND " + request.getData());

        return response;
    }

}
