package de.spring.simple.avro.registry.producer.configuration;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import de.spring.simple.avro.registry.Joe;
import de.spring.simple.avro.registry.producer.service.ProducerService;
import de.spring.simple.avro.registry.producer.service.impl.ProducerServiceImpl;
import io.confluent.kafka.serializers.KafkaAvroSerializerConfig;

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
		properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBroker);
		properties.setProperty(ProducerConfig.ACKS_CONFIG, "all");
		properties.setProperty(ProducerConfig.RETRIES_CONFIG, "10");
		properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");

		// Avro properties
		properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "io.confluent.kafka.serializers.KafkaAvroSerializer");
		properties.setProperty(KafkaAvroSerializerConfig.SCHEMA_REGISTRY_URL_CONFIG, schemaRegistryUrl);

		return new ProducerServiceImpl(new KafkaProducer<String, Joe>(properties));
	}
}
