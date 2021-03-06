package de.spring.webservices.endpoints;

import org.jdom2.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import de.spring.webservices.operations.Operations;
import de.spring.webservices.operations.Operations.RequestResponse;
import de.spring.webservices.server.auto.CustomBindingExampleRequest;
import de.spring.webservices.server.auto.CustomBindingExampleResponse;
import de.spring.webservices.server.auto.ExampleRequest;
import de.spring.webservices.server.auto.ExampleResponse;
import de.spring.webservices.services.ExampleService;


@Endpoint
public class ExampleEndPoint {
    private static final String NAMESPACE_URI = "http://schemas.gumartinm.name/spring-ws/example";
    
    private final Operations.RequestResponse
    	<CustomBindingExampleResponse, CustomBindingExampleRequest> customBindingExampleService;  
    
    private final ExampleService exampleService;
    
    @Autowired
	public ExampleEndPoint(
            RequestResponse<CustomBindingExampleResponse, CustomBindingExampleRequest> customBindingExampleService,
            ExampleService exampleService) {
	    this.customBindingExampleService = customBindingExampleService;
	    this.exampleService = exampleService;
    }

    // WARNING!!! RESPONSE OBJECT MUST BE ANNOTATED WITH @XmlRootElement
    // (ExampleResponse has such annotation)
    // OTHERWISE YOU WILL SEE THIS WEIRD ERROR: "No adapter for endPoint"
    // see: http://stackoverflow.com/a/22227806	
    @PayloadRoot(localPart = "ExampleRequest", namespace = NAMESPACE_URI)
    @ResponsePayload
    public ExampleResponse exampleResponse(
            @RequestPayload final ExampleRequest request,
            @RequestPayload final Element element,
            final MessageContext messageContext) {

        return this.exampleService.doResponse(request);
    }
   
    // WARNING!!! RESPONSE OBJECT MUST BE ANNOTATED WITH @XmlRootElement
    // (ExampleResponse has such annotation)
    // OTHERWISE YOU WILL SEE THIS WEIRD ERROR: "No adapter for endPoint"
    // see: http://stackoverflow.com/a/22227806 
    @PayloadRoot(localPart = "CustomBindingExampleRequest", namespace = NAMESPACE_URI)
    @ResponsePayload
    public CustomBindingExampleResponse cuntomBindingExampleResponse(
            @RequestPayload final CustomBindingExampleRequest requestObject,
            @RequestPayload final Element element,
            final MessageContext messageContext) {

        return this.customBindingExampleService.requestResponse(requestObject);
    }
}

