package de.example.spring.pact.consumer.infrastructure.feign.client;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.http.HttpMessageConvertersAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.netflix.ribbon.RibbonAutoConfiguration;
import org.springframework.cloud.netflix.ribbon.StaticServerList;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.cloud.openfeign.ribbon.FeignRibbonClientAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.ServerList;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRuleMk2;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.MockProviderConfig;
import au.com.dius.pact.model.RequestResponsePact;
import de.example.spring.pact.consumer.infrastructure.repository.dto.CarDto;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { CarFeignClientPACIntegrationTest.RibbonTestConfiguration.class })
@EnableFeignClients("de.example.spring.pact.consumer.infrastructure.feign.client")
@ImportAutoConfiguration({ RibbonAutoConfiguration.class,
						   FeignRibbonClientAutoConfiguration.class,
						   FeignAutoConfiguration.class,
						   HttpMessageConvertersAutoConfiguration.class })
public class CarFeignClientPACIntegrationTest {
	private static final String PROVIDER = "web-services-spring-pact-server";
	private static final String CONSUMER = "web-services-spring-pact-client";
	private static final String STATE = "test state";

    @Inject
    private CarFeignClient carFeignClient;

    @Rule
    public PactProviderRuleMk2 mockProvider =
    	new PactProviderRuleMk2(PROVIDER, MockProviderConfig.LOCALHOST, 8080, this);


    @Pact(provider = PROVIDER, consumer = CONSUMER)
    public RequestResponsePact createFragment(PactDslWithProvider builder) {
    	Map<String, String> headers = new HashMap<>();
    	headers.put("Content-Type","application/json");
        return builder
            .given(STATE)
            .uponReceiving("CarFeignClient test findAll")
                .path("/cars/")
                .method("GET")
            .willRespondWith()
                .status(200)
                .headers(headers)
                .body("[{\"brand\":\"Ford\", \"engine\": \"Diesel\"}]")
            .toPact();
    }

    @Test
    @PactVerification(PROVIDER)
    public void shouldFindAll() {
        List<CarDto> carDtos = carFeignClient.findAll();

        CarDto carDto = carDtos.get(0);
        assertThat(carDtos.size(), is(1));
        assertThat(carDto.getBrand(), is("Ford"));
        assertThat(carDto.getEngine(), is("Diesel"));
    }

	@Configuration
	public static class RibbonTestConfiguration {

		@Bean
	    public ServerList<Server> serverList() {
			return new StaticServerList<>(new Server(MockProviderConfig.LOCALHOST, 8080));
	    }

	}
}
