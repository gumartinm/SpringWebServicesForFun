package de.example.spring.pact;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.tomakehurst.wiremock.common.ClasspathFileSource;
import com.github.tomakehurst.wiremock.junit.WireMockClassRule;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class },
                webEnvironment = WebEnvironment.NONE)
@ActiveProfiles("test")
public class ApplicationIntegrationTest {
	
	@ClassRule
	public static WireMockClassRule wireMockRule = new WireMockClassRule(	
		options().port(56564).fileSource(new ClasspathFileSource("wiremock")));

	@Test
	public void shouldFindAllCars() {
		
	}

}
