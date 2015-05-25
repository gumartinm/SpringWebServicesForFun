package de.spring.webservices.client;

import name.gumartinm.spring_ws.example.CustomBindingExampleRequest;
import name.gumartinm.spring_ws.example.CustomBindingExampleResponse;
import name.gumartinm.spring_ws.example.ExampleRequest;
import name.gumartinm.spring_ws.example.ExampleResponse;
import name.gumartinm.spring_ws.example.Examples;
import name.gumartinm.spring_ws.example.ExamplesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.client.core.WebServiceTemplate;

/**
 * Someone could write code like this one in order to send and receive
 * information from our Web Services.
 * 
 */
public class ExampleClientService {
    private final WebServiceTemplate webServiceTemplate;

    @Autowired
    public ExampleClientService(WebServiceTemplate webServiceTemplate) {
	    this.webServiceTemplate = webServiceTemplate;
    }

	public ExampleResponse sendAndReceiveJava() {
        final ExampleRequest exampleRequest = new ExampleRequest();
        exampleRequest.setData("SCARLETT JAVA. IT IS CANON.");

        final Examples exampleService = new ExamplesService().getExamplesSoap12();
        final ExampleResponse exampleResponse = exampleService.example(exampleRequest);
        
        return exampleResponse;
    }
    
	public ExampleResponse sendAndReceiveSpring() {
        final ExampleRequest exampleRequest = new ExampleRequest();
        exampleRequest.setData("SCARLETT SPRING. IT IS CANON.");

        final ExampleResponse exampleResponse = (ExampleResponse)
                this.webServiceTemplate.marshalSendAndReceive(exampleRequest);
        
        return exampleResponse;
    }
	
	public CustomBindingExampleResponse sendAndReceiveJavaCustom() {
        final CustomBindingExampleRequest customBindingxampleRequest =
        		new CustomBindingExampleRequest();
        customBindingxampleRequest.setData("CUSTOM BINDING JAVA. SCARLETT. IT IS CANON.");
  
        final Examples exampleService = new ExamplesService().getExamplesSoap12();
        final CustomBindingExampleResponse customBindingExampleResponse =
                exampleService.customBindingExample(customBindingxampleRequest);
        
        return customBindingExampleResponse;
    }
	
	public CustomBindingExampleResponse sendAndReceiveSpringCustom() {
        final CustomBindingExampleRequest customBindingxampleRequest =
        		new CustomBindingExampleRequest();
        customBindingxampleRequest.setData("CUSTOM BINDING SPRING. SCARLETT. IT IS CANON.");

        final CustomBindingExampleResponse customBindingExampleResponse =
        		(CustomBindingExampleResponse) this.webServiceTemplate
        		.marshalSendAndReceive(customBindingxampleRequest);
        
        return customBindingExampleResponse;
    }
}
