package de.spring.simple.avro.registry.producer.configuration;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import de.spring.simple.avro.registry.Joe;
import de.spring.simple.avro.registry.producer.service.ProducerService;
import de.spring.simple.avro.registry.producer.service.impl.ProducerServiceImpl;

@Configuration
public class ServiceConfiguration {
	@Value("${kafka.broker}")
	String kafkaBroker;

	@Value("${schema.registry.url}")
	String schemaRegistryUrl;

	@Bean
	public ProducerService producerService() {

		Properties properties = new Properties();
		// Kafka Properties
		properties.setProperty("bootstrap.servers", kafkaBroker);
		properties.setProperty("acks", "all");
		properties.setProperty("retries", "10");

		// Avro properties
		properties.setProperty("key.serializer", "io.confluent.kafka.serializers.KafkaAvroSerializer");
		properties.setProperty("value.serializer", "io.confluent.kafka.serializers.KafkaAvroSerializer");
		properties.setProperty("schema.registry.url", schemaRegistryUrl);

		return new ProducerServiceImpl(new KafkaProducer<String, Joe>(properties));
	}
}
