package de.spring.webservices.business;

import de.spring.webservices.auto.ExampleRequest;
import de.spring.webservices.auto.ExampleResponse;
import de.spring.webservices.auto.ObjectFactory;
import de.spring.webservices.porttypes.PortType;

/**
 * Example of business class
 *
 */
public class BusinessExample implements PortType.RequestResponse<ExampleResponse, ExampleRequest> {

    @Override
    public ExampleResponse requestResponse(ExampleRequest request) {
        final ExampleResponse response = new ObjectFactory().createExampleResponse();
        
        response.setData("SNAKE EYES AND " + request.getData());
        
        return response;
    }

}
