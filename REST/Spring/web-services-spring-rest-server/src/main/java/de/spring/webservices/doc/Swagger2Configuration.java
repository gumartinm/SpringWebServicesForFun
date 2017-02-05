package de.spring.webservices.doc;

import static com.google.common.collect.Lists.newArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger1.annotations.EnableSwagger;

@Configuration
@EnableWebMvc
@EnableSwagger
@ComponentScan("de.spring.webservices.rest.controller")
public class Swagger2Configuration {
	
	@Bean
	public Docket documentation() {
		return new Docket(DocumentationType.SWAGGER_12)
				.select()
					.apis(RequestHandlerSelectors.withMethodAnnotation(RequestMapping.class))
					.paths(PathSelectors.any())
					.build()
					.globalResponseMessage(RequestMethod.GET,
							newArrayList(new ResponseMessageBuilder()
									.code(500).message("Global server custom error message").build()))
		        .pathMapping("/")
		        .useDefaultResponseMessages(false)
		        .apiInfo(metadata())
		        .enable(true);
	}

	@Bean
	UiConfiguration uiConfig() {
		return UiConfiguration.DEFAULT;
	}
	
	private static ApiInfo metadata() {
		return new ApiInfoBuilder()
				.title("gumartinm REST API")
				.description("Gustavo Martin Morcuende")
				.version("1.0-SNAPSHOT")
		        .contact("gumartinm.name")
		        .build();
	}

}
