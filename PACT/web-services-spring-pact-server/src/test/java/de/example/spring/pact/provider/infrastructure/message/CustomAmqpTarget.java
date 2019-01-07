package de.example.spring.pact.provider.infrastructure.message;

import au.com.dius.pact.model.Interaction;
import au.com.dius.pact.model.ProviderState;
import au.com.dius.pact.provider.ConsumerInfo;
import au.com.dius.pact.provider.ProviderInfo;
import au.com.dius.pact.provider.ProviderVerifier;
import au.com.dius.pact.provider.junit.target.AmqpTarget;

import java.util.List;

// Taken from https://github.com/thombergs/code-examples/blob/de7fa53d554c7ff4495c9efdb41b12386494be0f/pact/pact-message-producer/src/test/java/io/reflectoring/CustomAmqpTarget.java
public class CustomAmqpTarget extends AmqpTarget {

    public CustomAmqpTarget(List<String> packagesToScan) {
        super(packagesToScan);
    }

    @Override
    protected ProviderVerifier setupVerifier(Interaction interaction, ProviderInfo provider, ConsumerInfo consumer) {
        ProviderVerifier verifier = new CustomProviderVerifier(getPackagesToScan());
        setupReporters(verifier, provider.getName(), interaction.getDescription());
        verifier.initialiseReporters(provider);
        verifier.reportVerificationForConsumer(consumer, provider);

        if (!interaction.getProviderStates().isEmpty()) {
            for (ProviderState state : interaction.getProviderStates()) {
                verifier.reportStateForInteraction(state.getName(), provider, consumer, true);
            }
        }
        verifier.reportInteractionDescription(interaction);
        return verifier;
    }
}
