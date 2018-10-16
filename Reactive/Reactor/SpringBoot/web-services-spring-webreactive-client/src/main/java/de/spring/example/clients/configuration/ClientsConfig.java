package de.spring.example.clients.configuration;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

import de.spring.example.clients.service.AdClientService;
import de.spring.example.clients.service.impl.AdClientServiceImpl;
import io.micrometer.core.instrument.MeterRegistry;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;

@Configuration
public class ClientsConfig {
	@Value("${app.uri.host.url}")
	private String uriHost;

	@Value("${app.uri.host.read-timeout}")
	private Integer readTimeOut;

	@Value("${app.uri.host.write-timeout}")
	private Integer writeTimeout;

	@Value("${app.uri.host.connection-timeout}")
	private Integer connectionTimeOut;

	@Bean
	public AdClientService adClientService(WebClient.Builder webClientBuilder, MeterRegistry registry) {
		WebClient webClient = webClientBuilder.build();
		return new AdClientServiceImpl(uriHost, webClient, registry);
	}

	@Bean
	public WebClient.Builder webClientBuilder() {
		ClientHttpConnector connector = new ReactorClientHttpConnector(options -> {
			options.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, connectionTimeOut)
				   .onChannelInit(channel -> {
					   channel.pipeline().addLast(new ReadTimeoutHandler(readTimeOut, TimeUnit.MILLISECONDS));
					   channel.pipeline().addLast(new WriteTimeoutHandler(writeTimeout, TimeUnit.MILLISECONDS));
					   return true;
				   });
			});

		WebClient.Builder webClientBuilder = WebClient.builder();
		return webClientBuilder
				.clientConnector(connector);
	}
}
