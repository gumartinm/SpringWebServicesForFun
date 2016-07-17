package de.spring.webservices.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import de.spring.webservices.client.auto.CustomBindingExampleResponse;
import de.spring.webservices.client.auto.ExampleResponse;

/**
 * This class is used just like a nice example about how to write and run client
 * code which will send data to and from the Web Services.
 * 
 */
public class MainTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(MainTest.class);
	
    public ApplicationContext context;

    /**
     * @param args
     */
    public static void main(final String[] args) {
        final MainTest test = new MainTest();

        test.context = new ClassPathXmlApplicationContext(
                "classpath:spring-configuration/ws/client-spring-configuration.xml");

        final ExampleClientService example =
        		(ExampleClientService) test.context.getBean("exampleClientService");

        LOGGER.info("ExampleResponse Java:");
        ExampleResponse response;
		try {
			response = example.sendAndReceiveJava();
			LOGGER.info(response.getData());
		} catch (Exception e) {
			LOGGER.info("ExampleResponse Java error:", e);
		}
        
        
        
        LOGGER.info("CustomBindingExampleResponse Java:");
        CustomBindingExampleResponse customBindingResponse;
		try {
			customBindingResponse = example.sendAndReceiveJavaCustom();
			LOGGER.info(customBindingResponse.getData());
		} catch (Exception e) {
			LOGGER.info("CustomBindingExampleResponse Java error:", e);
		}
        
        
        
        LOGGER.info("ExampleResponse Spring:");
        response = example.sendAndReceiveSpring();
        LOGGER.info(response.getData());
        
        
        LOGGER.info("CustomBindingExampleResponse Spring:");
        customBindingResponse = example.sendAndReceiveSpringCustom();
        LOGGER.info(customBindingResponse.getData());
    }
}
