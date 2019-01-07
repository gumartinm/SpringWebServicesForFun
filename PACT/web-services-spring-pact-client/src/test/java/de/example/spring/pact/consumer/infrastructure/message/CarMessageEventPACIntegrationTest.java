package de.example.spring.pact.consumer.infrastructure.message;

import au.com.dius.pact.consumer.MessagePactBuilder;
import au.com.dius.pact.consumer.MessagePactProviderRule;
import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.model.v3.messaging.MessagePact;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.example.spring.pact.provider.domain.entity.CarMessageEvent;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class CarMessageEventPACIntegrationTest {
	private static final String PROVIDER = "web-services-spring-pact-server.message.topic";
	private static final String CONSUMER = "web-services-spring-pact-client.message";
	private static final String STATE = "test carmessageevent state";
	private static final String MESSAGE_DESCRIPTION = "A CarMessageEvent";
    
    private final ObjectMapper mapper = new ObjectMapper();

    private byte[] currentMessage;
    
    @Rule
    public final MessagePactProviderRule mockProvider = new MessagePactProviderRule(this);

    @Pact(provider = PROVIDER, consumer = CONSUMER)
    public MessagePact createPact(MessagePactBuilder builder) {
        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", "application/json");

        PactDslJsonBody body = new PactDslJsonBody();
        body.stringValue("brand", "Ford");
        body.stringValue("engine", "Diesel");
        
        return builder.given(STATE)
                .expectsToReceive(MESSAGE_DESCRIPTION)
                .withContent(body)
                .withMetadata(metadata)
                .toPact();
    }

    @Test
    @PactVerification({PROVIDER, STATE})
    public void shouldVerifyCarMessageEvent() throws Exception {
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
