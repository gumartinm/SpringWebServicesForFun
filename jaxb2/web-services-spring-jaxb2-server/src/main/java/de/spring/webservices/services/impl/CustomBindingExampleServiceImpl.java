package de.spring.webservices.services.impl;

//import java.util.ArrayList;
//import java.util.List;

import org.springframework.stereotype.Service;



//import de.spring.webservices.exceptions.BusinessException;
//import de.spring.webservices.exceptions.CustomBusinessException;
import de.spring.webservices.auto.CustomBindingExampleRequest;
import de.spring.webservices.auto.CustomBindingExampleResponse;
import de.spring.webservices.auto.ParentEnumType;
import de.spring.webservices.operations.Operations;


@Service("customBindingExampleService")
public class CustomBindingExampleServiceImpl implements
	Operations.RequestResponse<CustomBindingExampleResponse, CustomBindingExampleRequest> {

	
	@Override
    public CustomBindingExampleResponse requestResponse(final CustomBindingExampleRequest request) {
		
		
		// Example about how works org.springframework.ws.soap.server.endpoint.SoapFaultMappingExceptionResolver
		// see soap-ws.xml Spring configuration file.
//		throw new BusinessException("This feature has not been implemented yet.");
		
		// Example about how works de.spring.webservices.endpoints.MyCustomExceptionResolver
		// see soap-ws.xml Spring configuration file.
//		List<String> arguments = new ArrayList<>();
//		arguments.add("ARGUMENT 1");
//		arguments.add("ARGUMENT 2");
//		throw new CustomBusinessException("This feature has not been implemented yet.", arguments);
		
        CustomBindingExampleResponse response = new CustomBindingExampleResponse();

        response.setData("CUSTOM BINDING SNAKE EYES AND " + request.getData());
        response.setParentEnum(ParentEnumType.FIRST);

        return response;
    }

}
