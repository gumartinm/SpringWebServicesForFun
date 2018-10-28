package de.spring.simple.avro.registry.consumer.configuration;

import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import de.spring.simple.avro.registry.Joe;
import de.spring.simple.avro.registry.consumer.service.ConsumerService;
import de.spring.simple.avro.registry.consumer.service.impl.ConsumerServiceImpl;

@Configuration
public class ServiceConfiguration {
	@Value("${kafka.broker}")
	String kafkaBroker;

	@Value("${schema.registry.url}")
	String schemaRegistryUrl;

	@Value("${kafka.topic}")
	private String topic;

	@Bean
	public ConsumerService consumerService() {

		Properties properties = new Properties();
		// Kafka Properties
		properties.setProperty("bootstrap.servers", kafkaBroker);
		properties.setProperty("group.id", "ConsumerAvor");

		// Avro properties
		properties.setProperty("key.serializer", "io.confluent.kafka.serializers.KafkaAvroSerializer");
		properties.setProperty("value.serializer", "io.confluent.kafka.serializers.KafkaAvroSerializer");
		properties.setProperty("schema.registry.url", schemaRegistryUrl);

		KafkaConsumer<String, Joe> kafkaConsumer = new KafkaConsumer<String, Joe>(properties);
		kafkaConsumer.subscribe(Collections.singletonList(topic));
		return new ConsumerServiceImpl(kafkaConsumer);
	}
}
