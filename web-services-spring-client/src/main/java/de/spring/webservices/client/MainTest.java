package de.spring.webservices.client;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * This class is used just like a nice example about how to write and run client
 * code which will send data to and from the Web Services.
 * 
 */
public class MainTest {
    public ApplicationContext context;

    /**
     * @param args
     */
    public static void main(final String[] args) {
        final MainTest test = new MainTest();

        test.context = new ClassPathXmlApplicationContext(
                "client-spring-configuration.xml");

        final ExampleClientService example =
        		(ExampleClientService) test.context.getBean("exampleClient");

        example.sendAndReceiveJava();
        example.sendAndReceiveJavaCustom();
        example.sendAndReceiveSpring();
        example.sendAndReceiveSpringCustom();
    }
}
