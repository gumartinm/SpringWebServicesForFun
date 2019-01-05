package de.example.spring.pact.consumer.infrastructure.feign.client;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import javax.inject.Inject;

import org.junit.ClassRule;
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

import com.atlassian.ta.wiremockpactgenerator.WireMockPactGenerator;
import com.github.tomakehurst.wiremock.common.ClasspathFileSource;
import com.github.tomakehurst.wiremock.junit.WireMockClassRule;
import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.ServerList;

import de.example.spring.pact.consumer.infrastructure.repository.dto.CarDto;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { CarFeignClientWireMocPACGeneratorIntegrationTest.RibbonTestConfiguration.class })
@EnableFeignClients("de.example.spring.pact.consumer.infrastructure.feign.client")
@ImportAutoConfiguration({ RibbonAutoConfiguration.class,
						   FeignRibbonClientAutoConfiguration.class,
						   FeignAutoConfiguration.class,
						   HttpMessageConvertersAutoConfiguration.class })
public class CarFeignClientWireMocPACGeneratorIntegrationTest {

    @Inject
    CarFeignClient carFeignClient;

	@ClassRule
	public static WireMockClassRule wireMockRule = new WireMockClassRule(	
		options().dynamicPort().fileSource(new ClasspathFileSource("wiremock")));
	
    @Test
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
			wireMockRule.addMockServiceRequestListener(WireMockPactGenerator
			        .builder("cars_wiremockpact_consumer", "cars_wiremockpact_provider")
			        .withRequestPathWhitelist("/cars/")
			        .build());
			
			return new StaticServerList<>(new Server("localhost", wireMockRule.port()));
	    }

	}
}
