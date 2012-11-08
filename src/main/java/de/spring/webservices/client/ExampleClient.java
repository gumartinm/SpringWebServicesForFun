package de.spring.webservices.client;

import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.saaj.SaajSoapMessageFactory;

import localhost._8888.spring_ws.example.ExampleResponse;
import localhost._8888.spring_ws.example.Examples;
import localhost._8888.spring_ws.example.ExamplesService;
import localhost._8888.spring_ws.example.ExampleRequest;

public class ExampleClient {
    private WebServiceTemplate webServiceTemplate;
    private String Uri;

    
    public void setWebServiceTemplate(WebServiceTemplate webServiceTemplate) {
        this.webServiceTemplate = webServiceTemplate;
    }
    
    public void setDefaultUri(String defaultUri) {
        this.Uri = defaultUri;
    }
    
    public void sendAndReceive() {
        //Examples exampleService = new ExamplesService().getExamplesSoap11();
        ExampleRequest exampleRequest = new ExampleRequest();
        exampleRequest.setData("I am John Smith.");
        //exampleService.example(exampleRequest);
        
        
        this.webServiceTemplate.setDefaultUri(this.Uri);
        ExampleResponse exampleResponse = 
                (ExampleResponse) this.webServiceTemplate.marshalSendAndReceive(exampleRequest);
        
        System.out.println(exampleResponse.getData());
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        ExampleClient prueba = new ExampleClient();
        SaajSoapMessageFactory joer = new SaajSoapMessageFactory();
        joer.afterPropertiesSet();
        WebServiceTemplate laleche = new WebServiceTemplate(joer);
        laleche.afterPropertiesSet();
        laleche.setMarshaller(new Jaxb2Marshaller());
        
        prueba.setDefaultUri("http://localhost:8888/spring-ws/example");
        prueba.setWebServiceTemplate(laleche);
        prueba.sendAndReceive();
    }
}
