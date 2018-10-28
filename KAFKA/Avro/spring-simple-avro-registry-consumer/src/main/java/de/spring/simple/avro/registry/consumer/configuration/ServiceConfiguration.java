package de.spring.simple.avro.registry.consumer.configuration;

import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import de.spring.simple.avro.registry.Joe;
import de.spring.simple.avro.registry.consumer.service.ConsumerService;
import de.spring.simple.avro.registry.consumer.service.impl.ConsumerServiceImpl;
import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig;

@Configuration
public class ServiceConfiguration {
	@Value("${kafka.broker}")
	String kafkaBroker;

	@Value("${schema.registry.url}")
	String schemaRegistryUrl;

	@Value("${kafka.topic}")
	private String topic;

	@Value("${kafka.consumer-group}")
	private String consumerGroup;

	@Bean
	public ConsumerService consumerService() {

		Properties properties = new Properties();
		// Kafka Properties
		properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBroker);
		properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, consumerGroup);
		properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");

		// Avro properties
		properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "io.confluent.kafka.serializers.KafkaAvroDeserializer");
		properties.setProperty(KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG, "true");
		properties.setProperty(KafkaAvroDeserializerConfig.SCHEMA_REGISTRY_URL_CONFIG, schemaRegistryUrl);
		KafkaConsumer<String, Joe> kafkaConsumer = new KafkaConsumer<String, Joe>(properties);
		kafkaConsumer.subscribe(Collections.singletonList(topic));
		return new ConsumerServiceImpl(kafkaConsumer);
	}
}
