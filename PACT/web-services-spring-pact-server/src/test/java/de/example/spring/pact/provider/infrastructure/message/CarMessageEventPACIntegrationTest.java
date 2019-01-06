package de.example.spring.pact.provider.infrastructure.message;

import java.io.IOException;

import org.junit.Ignore;
import org.junit.runner.RunWith;

import com.fasterxml.jackson.databind.ObjectMapper;

import au.com.dius.pact.provider.PactVerifyProvider;
import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.RestPactRunner;
import au.com.dius.pact.provider.junit.loader.PactBroker;
import au.com.dius.pact.provider.junit.target.AmqpTarget;
import au.com.dius.pact.provider.junit.target.Target;
import au.com.dius.pact.provider.junit.target.TestTarget;
import de.example.spring.pact.provider.domain.entity.CarMessageEvent;

@Ignore
@RunWith(RestPactRunner.class)
@Provider(CarMessageEventPACIntegrationTest.PROVIDER)
@PactBroker(host = "${pactbroker.host:localhost}", port = "${pactbroker.port:80}")
public class CarMessageEventPACIntegrationTest {
	protected static final String PROVIDER = "web-services-spring-pact-server.message.topic";

    private final ObjectMapper mapper = new ObjectMapper();

	@TestTarget
    public final Target target = new AmqpTarget();
	
    @PactVerifyProvider(value = CarMessageEventPACIntegrationTest.PROVIDER)
    public String shouldVerifyCarMessageEvent() throws IOException {
    	CarMessageEvent carMessageEvent = new CarMessageEvent();
    	carMessageEvent.setBrand("Ford");
    	carMessageEvent.setEngine("Diesel");
    	
    	return mapToString(carMessageEvent);
    }
    
    private String mapToString(CarMessageEvent carMessageEvent) throws IOException {
    	return mapper.writeValueAsString(carMessageEvent);
    }
}
