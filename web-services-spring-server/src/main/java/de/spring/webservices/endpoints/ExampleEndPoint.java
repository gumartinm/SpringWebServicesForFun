package de.spring.webservices.endpoints;

import org.jdom2.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import de.spring.webservices.auto.CustomBindingExampleRequest;
import de.spring.webservices.auto.CustomBindingExampleResponse;
import de.spring.webservices.auto.ExampleRequest;
import de.spring.webservices.auto.ExampleResponse;
import de.spring.webservices.operations.Operations;
import de.spring.webservices.operations.Operations.RequestResponse;


@Endpoint
public class ExampleEndPoint {
    private static final String NAMESPACE_URI = "http://gumartinm.name/spring-ws/example";
    
    private final Operations.RequestResponse
    	<CustomBindingExampleResponse, CustomBindingExampleRequest> customBindingExample;  
    
    @Autowired
	public ExampleEndPoint(
            RequestResponse<CustomBindingExampleResponse, CustomBindingExampleRequest> customBindingExample) {
	    this.customBindingExample = customBindingExample;
    }
	
    @PayloadRoot(localPart = "ExampleRequest", namespace = NAMESPACE_URI)
    @ResponsePayload
    public ExampleResponse order(
            @RequestPayload final ExampleRequest requestObject,
            @RequestPayload final Element element,
            final MessageContext messageContext) {

    	final ExampleResponse response = new ExampleResponse();

        response.setData("SNAKE EYES AND " + requestObject.getData());

        return response;
    }
    
    @PayloadRoot(localPart = "CustomBindingExampleRequest", namespace = NAMESPACE_URI)
    @ResponsePayload
    public CustomBindingExampleResponse order(
            @RequestPayload final CustomBindingExampleRequest requestObject,
            @RequestPayload final Element element,
            final MessageContext messageContext) {

        return this.customBindingExample.requestResponse(requestObject);
    }
}

