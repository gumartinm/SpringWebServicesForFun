package de.spring.webservices.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import de.spring.webservices.auto.CustomBindingExampleResponse;
import de.spring.webservices.auto.ExampleResponse;

/**
 * This class is used just like a nice example about how to write and run client
 * code which will send data to and from the Web Services.
 * 
 */
public class MainTest {
	private static final Logger logger = LoggerFactory.getLogger(MainTest.class);
	
    public ApplicationContext context;

    /**
     * @param args
     */
    public static void main(final String[] args) {
        final MainTest test = new MainTest();

        test.context = new ClassPathXmlApplicationContext(
                "classpath:spring-configuration/ws/client-spring-configuration.xml");

        final ExampleClientService example =
        		(ExampleClientService) test.context.getBean("exampleClient");

//  maven-jaxb2-plugin DOESN'T CREATE @WebService, @WebServiceClient y @WebEndpoint
//        logger.info("ExampleResponse Java:");
//        ExampleResponse response = example.sendAndReceiveJava();
//        logger.info(response.getData());
        
        
//        logger.info("CustomBindingExampleResponse Java:");
//        CustomBindingExampleResponse customBindingResponse = example.sendAndReceiveJavaCustom();
//        logger.info(customBindingResponse.getData());
        
        
        logger.info("ExampleResponse Spring:");
        ExampleResponse response = example.sendAndReceiveSpring();
        logger.info(response.getData());
        
        
        logger.info("CustomBindingExampleResponse Spring:");
        CustomBindingExampleResponse customBindingResponse = example.sendAndReceiveSpringCustom();
        logger.info(customBindingResponse.getData());
    }
}