package de.spring.webservices.ws;

import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.xpath.XPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import de.spring.webservices.service.TestService;



@Endpoint
public class HolidayEndpoint {
    private static final String NAMESPACE_URI = "http://localhost:8080/spring-ws/orders";
    private final XPath notificationExpression;
    private TestService testService = new TestService();

    @Autowired
    public HolidayEndpoint(final TestService testService) throws JDOMException {
        this.testService = testService;
        final Namespace namespace = Namespace.getNamespace("", NAMESPACE_URI);
        notificationExpression = XPath.newInstance("//ord:GetOrdersRequest");
        notificationExpression.addNamespace(namespace);
    }

    @PayloadRoot(localPart = "GetOrdersRequest", namespace = NAMESPACE_URI)
    public void order(@RequestPayload final Element notificationRequest) throws Exception {

        final String notification = notificationExpression.valueOf(notificationRequest);
        //humanResourceService.bookHoliday(startDate, endDate, name);
        testService.setNotification(notification);
    }


    @PayloadRoot(localPart = "GetOrdersResponse", namespace = NAMESPACE_URI)
    @ResponsePayload
    public Element getOrder(@RequestPayload final Element element) {
        return new Element("hola", Namespace.getNamespace("", NAMESPACE_URI));
        // testService.getNotification();
    }
}
