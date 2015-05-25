package de.spring.webservices.client;

import static org.springframework.ws.test.client.RequestMatchers.payload;
import static org.springframework.ws.test.client.ResponseCreators.withPayload;
import static org.junit.Assert.*;

import javax.xml.transform.Source;

import name.gumartinm.spring_ws.example.ExampleResponse;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.test.client.MockWebServiceServer;
import org.springframework.xml.transform.StringSource;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/client-spring-configuration.xml")
public class ExampleClientServiceIntegrationTest {

	@Autowired
	ExampleClientService exampleClientService;
	
    @Autowired
    private WebServiceTemplate webServiceTemplate;

    private MockWebServiceServer mockServer;

    @Before
    public void createServer() throws Exception {
        mockServer = MockWebServiceServer.createServer(webServiceTemplate);
    }

    @Test
    public void customerClient() throws Exception {
        final Source requestPayload = new StringSource(
                "<ExampleRequest xmlns='http://gumartinm.name/spring-ws/example'>"
                        + "<data>SCARLETT SPRING. IT IS CANON.</data>"
                        + "</ExampleRequest>");
        final Source responsePayload = new StringSource(
                "<ns2:ExampleResponse xmlns:ns2='http://gumartinm.name/spring-ws/example'>"
                        + "<ns2:data>SNAKE EYES AND SCARLETT SPRING. IT IS CANON.</ns2:data>"
                        + "</ns2:ExampleResponse>");
        mockServer.expect(payload(requestPayload)).andRespond(
                withPayload(responsePayload));
        
        final ExampleResponse exampleResponse = exampleClientService.sendAndReceiveSpring();

        assertEquals(exampleResponse.getData(), "SNAKE EYES AND SCARLETT SPRING. IT IS CANON.");
        
        mockServer.verify();
    }
}

