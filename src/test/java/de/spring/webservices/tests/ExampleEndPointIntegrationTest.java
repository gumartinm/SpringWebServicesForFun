package de.spring.webservices.tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.xml.transform.Source;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ws.test.server.MockWebServiceClient;
import org.springframework.xml.transform.StringSource;
import static org.springframework.ws.test.server.RequestCreators.*;
import static org.springframework.ws.test.server.ResponseMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-configuration.xml")
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
      Source requestPayload = new StringSource(
      		"<ExampleRequest xmlns='http://localhost:8888/spring-ws/example'>" +
      		        "<data>Houston</data>" +
            "</ExampleRequest>");
      Source responsePayload = new StringSource(
              "<ns2:ExampleResponse xmlns:ns2='http://localhost:8888/spring-ws/example'>" +
              "     <ns2:data>SNAKE EYES AND Houston</ns2:data>" +
              "</ns2:ExampleResponse>");
      mockClient.sendRequest(withPayload(requestPayload)).andExpect(payload(responsePayload));
    }
}
