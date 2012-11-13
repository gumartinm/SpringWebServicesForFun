package de.spring.webservices.business;

import de.spring.webservices.auto.ExampleRequest;
import de.spring.webservices.auto.ExampleResponse;
import de.spring.webservices.auto.ObjectFactory;
import de.spring.webservices.operations.Operations;

/**
 * Example of business class
 *
 */
public class BusinessExample implements Operations.RequestResponse<ExampleResponse, ExampleRequest> {

    @Override
    public ExampleResponse requestResponse(final ExampleRequest request) {
        final ExampleResponse response = new ObjectFactory().createExampleResponse();
        
        response.setData("SNAKE EYES AND " + request.getData());
        
        return response;
    }

}
