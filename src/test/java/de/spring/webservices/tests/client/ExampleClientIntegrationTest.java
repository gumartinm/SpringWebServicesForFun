package de.spring.webservices.tests.client;

import static org.springframework.ws.test.client.RequestMatchers.payload;
import static org.springframework.ws.test.client.ResponseCreators.withPayload;

import javax.xml.transform.Source;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ws.test.client.MockWebServiceServer;
import org.springframework.xml.transform.StringSource;

import de.spring.webservices.client.ExampleClient;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/client-spring-configuration.xml")
public class ExampleClientIntegrationTest {

    @Autowired
    private ExampleClient client;

    private MockWebServiceServer mockServer;

    @Before
    public void createServer() throws Exception {
        // mockServer = MockWebServiceServer.createServer(client);
    }

    @Test
    public void customerClient() throws Exception {
        final Source requestPayload = new StringSource(
                "<ExampleRequest xmlns='http://localhost:8888/spring-ws/example'>"
                        + "<data>SCARLETT. IT IS CANON.</data>"
                        + "</ExampleRequest>");
        final Source responsePayload = new StringSource(
                "<ns2:ExampleResponse xmlns:ns2='http://localhost:8888/spring-ws/example'>"
                        + "<ns2:data>SNAKE EYES AND SCARLETT. IT IS CANON.</ns2:data>"
                        + "</ns2:ExampleResponse>");
        mockServer.expect(payload(requestPayload)).andRespond(
                withPayload(responsePayload));

        mockServer.verify();
    }
}
