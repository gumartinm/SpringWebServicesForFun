package de.spring.webservices.rest.infrastructure.configuration;

import org.springframework.boot.actuate.autoconfigure.info.ConditionalOnEnabledInfoContributor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import de.spring.webservices.rest.infrastructure.controller.info.CustomInfoContributor;

@Configuration
@ConditionalOnEnabledInfoContributor("custom")
public class InfoContributorConfiguration {

	  @Bean
	  CustomInfoContributor customInfoContributor() {
	    return new CustomInfoContributor();
	  }

}
