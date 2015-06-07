package de.spring.webservices.client;

import static org.junit.Assert.assertEquals;
import static org.springframework.ws.test.client.RequestMatchers.payload;
import static org.springframework.ws.test.client.ResponseCreators.withPayload;

import javax.xml.transform.Source;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.test.client.MockWebServiceServer;
import org.springframework.xml.transform.StringSource;

import de.spring.webservices.auto.CustomBindingExampleResponse;
import de.spring.webservices.auto.ExampleResponse;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring-configuration/ws/client-spring-configuration.xml")
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
        
        final ExampleResponse response = exampleClientService.sendAndReceiveSpring();

        assertEquals(response.getData(), "SNAKE EYES AND SCARLETT SPRING. IT IS CANON.");
        mockServer.verify();
    }
    
    @Test
    public void customerCustomClient() throws Exception { 
        final Source customRequestPayload = new StringSource(
                "<CustomBindingExampleRequest xmlns='http://gumartinm.name/spring-ws/example'>" +
                        "<data>CUSTOM BINDING SPRING. SCARLETT. IT IS CANON.</data>" +
                "</CustomBindingExampleRequest>");
        final Source customResponsePayload = new StringSource(
                "<ns2:CustomBindingExampleResponse xmlns:ns2='http://gumartinm.name/spring-ws/example'>" +
                        "<ns2:data>CUSTOM BINDING SNAKE EYES AND SCARLETT SPRING. IT IS CANON.</ns2:data>" +
                "</ns2:CustomBindingExampleResponse>");
        mockServer.expect(payload(customRequestPayload)).andRespond(
        		withPayload(customResponsePayload));
        
        final CustomBindingExampleResponse response = exampleClientService.sendAndReceiveSpringCustom();

        assertEquals(response.getData(), "CUSTOM BINDING SNAKE EYES AND SCARLETT SPRING. IT IS CANON.");
        mockServer.verify();
    }
}

