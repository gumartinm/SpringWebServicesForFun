package de.spring.webservices.endpoints;

import org.jdom2.Element;
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


@Endpoint
public class ExampleEndPoint {
    private static final String NAMESPACE_URI = "http://localhost:8888/spring-ws/example";
    private Operations.RequestResponse<ExampleResponse, ExampleRequest> example;
    private Operations.RequestResponse<CustomBindingExampleResponse, CustomBindingExampleRequest> customBindingExample;


    public ExampleEndPoint() {}


    @PayloadRoot(localPart = "ExampleRequest", namespace = NAMESPACE_URI)
    @ResponsePayload
    public ExampleResponse order(
            @RequestPayload final ExampleRequest requestObject,
            @RequestPayload final Element element,
            final MessageContext messageContext) {

        return example.requestResponse(requestObject);
    }

    @PayloadRoot(localPart = "CustomBindingExampleRequest", namespace = NAMESPACE_URI)
    @ResponsePayload
    public CustomBindingExampleResponse order(
            @RequestPayload final CustomBindingExampleRequest requestObject,
            @RequestPayload final Element element,
            final MessageContext messageContext) {

        return this.customBindingExample.requestResponse(requestObject);
    }


    /** Setter required by Spring **/
    public void setExample(
            final Operations.RequestResponse<ExampleResponse, ExampleRequest> example) {
        this.example = example;
    }

    public void setCustomBindingExample(
            final Operations.RequestResponse<CustomBindingExampleResponse, CustomBindingExampleRequest> customBindingExample) {
        this.customBindingExample = customBindingExample;
    }
}
