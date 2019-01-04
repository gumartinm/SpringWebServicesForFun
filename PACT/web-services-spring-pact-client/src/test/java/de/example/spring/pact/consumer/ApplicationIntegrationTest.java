package de.example.spring.pact.consumer;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.cloud.netflix.ribbon.StaticServerList;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.tomakehurst.wiremock.common.ClasspathFileSource;
import com.github.tomakehurst.wiremock.junit.WireMockClassRule;
import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.ServerList;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class,
						    ApplicationIntegrationTest.RibbonTestConfiguration.class },
                webEnvironment = WebEnvironment.NONE)
@ActiveProfiles("test")
public class ApplicationIntegrationTest {
	
	@ClassRule
	public static WireMockClassRule wireMockRule = new WireMockClassRule(	
		options().dynamicPort().fileSource(new ClasspathFileSource("wiremock")));

	@Test
	public void shouldFindAllCars() {
		
	}

	@Configuration
	public static class RibbonTestConfiguration {

		@Bean
	    public ServerList<Server> serverList() {
	      return new StaticServerList<>(new Server("localhost", wireMockRule.port()));
	    }

	}
}
