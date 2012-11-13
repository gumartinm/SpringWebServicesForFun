package de.spring.webservices.client;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * This class is used just like a nice example about how to write and run client
 * code which will send data to and from the Web Services.
 * 
 */
public class Test {
    public ApplicationContext context;

    /**
     * @param args
     */
    public static void main(final String[] args) {
        final Test test = new Test();

        test.context = new ClassPathXmlApplicationContext(
                "client-spring-configuration.xml");

        final ExampleClient example = (ExampleClient) test.context.getBean("exampleClient");

        example.sendAndReceive();
    }
}
