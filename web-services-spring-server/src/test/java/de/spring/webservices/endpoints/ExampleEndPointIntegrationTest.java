package de.spring.webservices.endpoints;

import static org.springframework.ws.test.server.RequestCreators.withPayload;
import static org.springframework.ws.test.server.ResponseMatchers.payload;

import javax.xml.transform.Source;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ws.test.server.MockWebServiceClient;
import org.springframework.xml.transform.StringSource;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-configuration/ws/soap-ws.xml")
public class ExampleEndPointIntegrationTest {

    @Autowired
    private ApplicationContext applicationContext;

    private MockWebServiceClient mockClient;

    @Before
    public void createClient() {
        mockClient = MockWebServiceClient.createClient(applicationContext);
    }

    @Test
    public void exampleEndpoint() throws Exception {
        final Source requestPayload = new StringSource(
                "<ExampleRequest xmlns='http://gumartinm.name/spring-ws/example'>" +
                        "<data>SCARLETT</data>" +
                "</ExampleRequest>");
        final Source responsePayload = new StringSource(
                "<ns2:ExampleResponse xmlns:ns2='http://gumartinm.name/spring-ws/example'>" +
                        "<ns2:data>SNAKE EYES AND SCARLETT</ns2:data>" +
                "</ns2:ExampleResponse>");
        mockClient.sendRequest(withPayload(requestPayload)).andExpect(
                payload(responsePayload));
    }
}

