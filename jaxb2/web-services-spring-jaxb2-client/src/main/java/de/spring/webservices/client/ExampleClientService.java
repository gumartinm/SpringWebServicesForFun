package de.spring.webservices.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.client.core.WebServiceTemplate;

import de.spring.webservices.auto.CustomBindingExampleRequest;
import de.spring.webservices.auto.CustomBindingExampleResponse;
import de.spring.webservices.auto.ExampleRequest;
import de.spring.webservices.auto.ExampleResponse;
//import de.spring.webservices.auto.Examples;
//import de.spring.webservices.auto.ExamplesService;
//import de.spring.webservices.auto.ParentEnumType;

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

//  maven-jaxb2-plugin DOESN'T CREATE @WebService, @WebServiceClient y @WebEndpoint
//	public ExampleResponse sendAndReceiveJava() {
//        final ExampleRequest exampleRequest = new ExampleRequest();
//        exampleRequest.setData("SCARLETT JAVA. IT IS CANON.");
//
//        final Examples exampleService = new ExamplesService().getExamplesSoap12();
//        final ExampleResponse exampleResponse = exampleService.example(exampleRequest);
//        
//        return exampleResponse;
//    }
    
	public ExampleResponse sendAndReceiveSpring() {
        final ExampleRequest exampleRequest = new ExampleRequest();
        exampleRequest.setData("SCARLETT SPRING. IT IS CANON.");

        final ExampleResponse exampleResponse = (ExampleResponse)
                this.webServiceTemplate.marshalSendAndReceive(exampleRequest);
        
        return exampleResponse;
    }
	
//  maven-jaxb2-plugin DOESN'T CREATE @WebService, @WebServiceClient y @WebEndpoint
//	public CustomBindingExampleResponse sendAndReceiveJavaCustom() {
//        final CustomBindingExampleRequest customBindingxampleRequest =
//        		new CustomBindingExampleRequest();
//        customBindingxampleRequest.setData("CUSTOM BINDING JAVA. SCARLETT. IT IS CANON.");
//        customBindingxampleRequest.setParentEnum(ParentEnumType.FIRST);
//  
//        final Examples exampleService = new ExamplesService().getExamplesSoap12();
//        final CustomBindingExampleResponse customBindingExampleResponse =
//                exampleService.customBindingExample(customBindingxampleRequest);
//        
//        return customBindingExampleResponse;
//    }
	
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
