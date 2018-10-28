package de.spring.simple.avro.registry.producer.service.impl;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import de.spring.simple.avro.registry.Joe;
import de.spring.simple.avro.registry.producer.service.ProducerService;

public class ProducerServiceImpl implements ProducerService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ProducerServiceImpl.class);

	private final Producer<String, Joe> kafkaProducer;

	@Value("${kafka.topic}")
	private String topic;

	public ProducerServiceImpl(Producer<String, Joe> kafkaProducer) {
		this.kafkaProducer = kafkaProducer;
	}

	@Override
	public void sendJoe(Joe joe) {
		ProducerRecord<String, Joe> record = new ProducerRecord<String, Joe>(topic, joe);

		kafkaProducer.send(record, new Callback() {

			@Override
			public void onCompletion(RecordMetadata metadata, Exception exception) {

				if (exception != null) {
					LOGGER.error("There was some error.", exception);
				}
			}
		});

		kafkaProducer.flush();
		kafkaProducer.close();
	}
}
