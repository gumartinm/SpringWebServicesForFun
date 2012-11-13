package de.spring.webservices.client;

import localhost._8888.spring_ws.example.ExampleRequest;
import localhost._8888.spring_ws.example.ExampleResponse;
import localhost._8888.spring_ws.example.Examples;
import localhost._8888.spring_ws.example.ExamplesService;
import localhost._8888.spring_ws.example.ObjectFactory;

import org.springframework.ws.client.core.WebServiceTemplate;

/**
 * Someone could write code like this one in order to send and receive
 * information from our Web Services.
 * 
 */
public class ExampleClient {
    private WebServiceTemplate webServiceTemplate;
    private String Uri;


    public void setWebServiceTemplate(
            final WebServiceTemplate webServiceTemplate) {
        this.webServiceTemplate = webServiceTemplate;
    }

    public void setDefaultUri(final String defaultUri) {
        this.Uri = defaultUri;
    }

    public void sendAndReceive() {
        final Examples exampleService = new ExamplesService().getExamplesSoap11();
        final ExampleRequest exampleRequest = new ObjectFactory().createExampleRequest();
        exampleRequest.setData("SCARLETT. IT IS CANON.");

        //There are two options O.o:

        //1. Through Java:
        ExampleResponse exampleResponse = exampleService.example(exampleRequest);
        System.out.println(exampleResponse.getData());

        //2. Using Spring:
        this.webServiceTemplate.setDefaultUri(this.Uri);
        exampleResponse = (ExampleResponse)
                this.webServiceTemplate.marshalSendAndReceive(exampleRequest);

        System.out.println(exampleResponse.getData());
    }
}
