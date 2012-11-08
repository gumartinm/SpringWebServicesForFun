package de.spring.webservices.endpoints;

import org.jdom2.Element;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import de.spring.webservices.auto.ExampleRequest;
import de.spring.webservices.auto.ExampleResponse;
import de.spring.webservices.porttypes.PortType;


@Endpoint
public class ExampleEndPoint {
    private static final String NAMESPACE_URI = "http://localhost:8888/spring-ws/example";
    private PortType.RequestResponse<ExampleResponse, ExampleRequest> example;
    

    public ExampleEndPoint() {}

    /*Este codigo debe ser escrito por los usuarios :(*/
    /*Los usuarios debe hacer lo siguiente en este proyecto:
     * 1. Crearse sus propios xsds
     * 2. Auto generar los objetos Request/Response asociados con sus xsds
     * 3. Crear los tests de integracion
     * 4. Generar este código y el setter para inyectar con Spring en este código 
     * la clase que implementa el interfaz WebService. Esta clase será implementada en un proyecto
     * de negocio.
     * 
     * Los usuarios deben hacer lo siguiente en sus proyectos:
     * 1. Implementar la clase del interfaz WebService
     * 2. Generar bean con dicha clase en su proyecto
     * 3. Inyectar ese bean en el bean de esta clase (lo dicho en puntos 4 y 5)
     * LOS USUARIOS TIENEN QUE HACER DEMASIADAS COSAS????? */
    @PayloadRoot(localPart = "ExampleRequest", namespace = NAMESPACE_URI)
    @ResponsePayload
    public ExampleResponse order(
            @RequestPayload final ExampleRequest requestObject,
            @RequestPayload final Element element,
            final MessageContext messageContext) {
        
            return example.requestResponse(requestObject);
    }
    

    /** Setter required by Spring **/
    public void setExample(
            PortType.RequestResponse<ExampleResponse, ExampleRequest> example) {
        this.example = example;
    }
}
