package de.example.spring.pact.provider.infrastructure.message;

import au.com.dius.pact.model.Interaction;
import au.com.dius.pact.model.v3.messaging.Message;
import au.com.dius.pact.provider.*;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.util.ConfigurationBuilder;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

// Taken from https://github.com/thombergs/code-examples/blob/de7fa53d554c7ff4495c9efdb41b12386494be0f/pact/pact-message-producer/src/test/java/io/reflectoring/CustomProviderVerifier.java
public class CustomProviderVerifier extends ProviderVerifier {
    private List<String> packagesToScan;

    public CustomProviderVerifier(List<String> packagesToScan) {
        this.packagesToScan = packagesToScan;
    }


    @Override
    public boolean verifyResponseByInvokingProviderMethods(IProviderInfo providerInfo, IConsumerInfo consumer,
                                                           Interaction interaction, String interactionMessage, Map failures) {
        try {

            ConfigurationBuilder configurationBuilder = new ConfigurationBuilder()
                    .setScanners(new MethodAnnotationsScanner())
                    .forPackages(packagesToScan.toArray(new String[]{}));

            Reflections reflections = new Reflections(configurationBuilder);
            Set<Method> methodsAnnotatedWith = reflections.getMethodsAnnotatedWith(PactVerifyProvider.class);
            Set<Method> providerMethods = methodsAnnotatedWith.stream()
                    .filter(m -> {
                        PactVerifyProvider annotation = m.getAnnotation(PactVerifyProvider.class);
                        return annotation.value().equals(((Interaction)interaction).getDescription());
                    })
                    .collect(Collectors.toSet());

            if (providerMethods.isEmpty()) {
                throw new RuntimeException("No annotated methods were found for interaction " +
                        "'${interaction.description}'. You need to provide a method annotated with " +
                        "@PactVerifyProvider(\"${interaction.description}\") that returns the message contents.");
            } else {
                if (interaction instanceof Message) {
                    verifyMessagePact(providerMethods, (Message) interaction, interactionMessage, failures);
                } else {
                    throw new RuntimeException("only supports Message interactions!");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("verification failed", e);
        }
        return true;
    }
}
