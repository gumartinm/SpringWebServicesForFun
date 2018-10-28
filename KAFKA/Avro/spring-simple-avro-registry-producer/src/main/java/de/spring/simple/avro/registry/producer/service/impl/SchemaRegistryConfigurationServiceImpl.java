package de.spring.simple.avro.registry.producer.service.impl;

import org.springframework.web.client.RestTemplate;

import de.spring.simple.avro.registry.producer.service.SchemaRegistryConfigurationService;

public class SchemaRegistryConfigurationServiceImpl implements SchemaRegistryConfigurationService {
	private static final String JOE_CONFIGURATION = "/config/Joe";

	private final String uriHost;
	private final RestTemplate restTemplate;

	public SchemaRegistryConfigurationServiceImpl(String uriHost, RestTemplate restTemplate) {
		this.uriHost = uriHost;
		this.restTemplate = restTemplate;
	}

	@Override
	public void configure() {


	}

}
