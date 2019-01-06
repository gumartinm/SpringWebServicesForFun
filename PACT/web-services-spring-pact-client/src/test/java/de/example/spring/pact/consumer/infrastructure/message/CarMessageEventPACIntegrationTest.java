package de.example.spring.pact.consumer.infrastructure.message;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import au.com.dius.pact.consumer.MessagePactBuilder;
import au.com.dius.pact.consumer.MessagePactProviderRule;
import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.model.v3.messaging.MessagePact;
import de.example.spring.pact.provider.domain.entity.CarMessageEvent;

public class CarMessageEventPACIntegrationTest {
	private static final String PROVIDER = "carmessageevent_pact_provider";
	private static final String CONSUMER = "carmessageevent_pact_consumer";
	private static final String STATE = "test carmessageevent state";
    
    private final ObjectMapper mapper = new ObjectMapper();

    private byte[] currentMessage;
    
    @Rule
    public final MessagePactProviderRule mockProvider = new MessagePactProviderRule(this);

    @Pact(provider = PROVIDER, consumer = CONSUMER)
    public MessagePact createPact(MessagePactBuilder builder) {    	
        PactDslJsonBody body = new PactDslJsonBody();
        body.stringValue("brand", "Ford");
        body.stringValue("engine", "Diesel");
        
        return builder.given(STATE)
                .expectsToReceive("A CarMessageEvent")
                .withContent(body)
                .toPact();
    }

    @Test
    @PactVerification({PROVIDER, STATE})
    public void test() throws Exception {
    	CarMessageEvent carMessageEvent = mapToCarMessageEvent(new String(currentMessage));
        assertThat(carMessageEvent.getBrand(), is("Ford"));
        assertThat(carMessageEvent.getEngine(), is("Diesel"));
    }

    // Used by MessagePactProviderRule
    public void setMessage(byte[] messageContents) {
        currentMessage = messageContents;
    }
    
    private CarMessageEvent mapToCarMessageEvent(String jsonMessage) throws IOException {
    	return mapper.readValue(jsonMessage, CarMessageEvent.class);
    }
}
