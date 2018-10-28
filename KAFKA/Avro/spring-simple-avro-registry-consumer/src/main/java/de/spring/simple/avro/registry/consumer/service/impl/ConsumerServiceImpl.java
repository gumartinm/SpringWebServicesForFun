package de.spring.simple.avro.registry.consumer.service.impl;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import de.spring.simple.avro.registry.Joe;
import de.spring.simple.avro.registry.consumer.service.ConsumerService;

public class ConsumerServiceImpl implements ConsumerService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerServiceImpl.class);

	private final KafkaConsumer<String, Joe> kafkaConsumer;

	@Value("${kafka.topic}")
	private String topic;

	public ConsumerServiceImpl(KafkaConsumer<String, Joe> kafkaConsumer) {
		this.kafkaConsumer = kafkaConsumer;
	}

	@Override
	public void consume() {

		try {
			while (true) {

				ConsumerRecords<String, Joe> records = kafkaConsumer.poll(1000);

				records.forEach(record -> {
					LOGGER.info("Joe record:");
					LOGGER.info("Name: {}", record.value().getName());
					LOGGER.info("Surname: {}", record.value().getSurname());
					LOGGER.info("Age: {}", record.value().getAge());
					LOGGER.info("Schema: {}", record.value().getSchema());
					LOGGER.info("To String value: {}", record.value().toString());
				});

				kafkaConsumer.commitSync();
			}		
		} finally {
			kafkaConsumer.close();
			LOGGER.info("Consumer was closed");
		}
	}
}
