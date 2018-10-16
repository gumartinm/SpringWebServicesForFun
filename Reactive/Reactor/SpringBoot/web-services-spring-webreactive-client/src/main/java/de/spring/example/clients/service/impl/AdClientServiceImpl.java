package de.spring.example.clients.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import de.spring.example.clients.service.AdClientService;
import de.spring.example.persistence.domain.Ad;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;
import reactor.core.publisher.Flux;

public class AdClientServiceImpl implements AdClientService {
	private static final Logger LOGGER = LoggerFactory.getLogger(AdClientServiceImpl.class);

	private static final String URI_PATH_FIND_ALL = "/ads/?page=no";
	
	private final WebClient webClient;
	private final String uriFindAll;
	private final MeterRegistry registry;
	
	public AdClientServiceImpl(String uriHost, WebClient webClient, MeterRegistry registry) {
		this.webClient = webClient;
		this.uriFindAll = uriHost + URI_PATH_FIND_ALL;
		// Spring Boot already registering one for us when having micrometer-registry-datadog.jar in classpath
		// and property management.metrics.export.datadog.enabled=true
		// this.registry = new DatadogMeterRegistry(new MyDatadogConfig(), Clock.SYSTEM);
		this.registry = registry;
	}

	@Override
	public Flux<Ad> findAll() {
		Tags accountTag = Tags.of("account", "test");
		Tags nameOneTag = Tags.of("name", "one");
		Tags nameTwoTag = Tags.of("name", "two");
		registry.counter("some.metric.about.user", accountTag).increment();

		LOGGER.info("Sending info by means of webClient");

		// micrometer after 30seconds will be sending {"metric":"some.metric.about.user","points":[[1539654599, 2.0]],"tags":["account:test","statistic:count"]}
		// 2.0 because there is 2 incrementes
		// if we stop incrementing (we stop calling the findAll method)
		// micrometer will keep sending {"metric":"some.metric.about.user","points":[[1539654599, 0.0]],"tags":["account:test","statistic:count"]}
		// 0.0 value because we stopped calling the findAll method and there are no more increments
		// registry.counter("some.metric.about.user", customTags).increment();

		registry.counter("some.metric.about.user", nameOneTag).increment();
		registry.counter("some.metric.about.user", nameTwoTag).increment();

		return webClient.get()
				.uri(this.uriFindAll).accept(MediaType.APPLICATION_JSON_UTF8)
				.exchange()
				.flatMapMany(clientResponse -> clientResponse.bodyToFlux(Ad.class));

	}
}
