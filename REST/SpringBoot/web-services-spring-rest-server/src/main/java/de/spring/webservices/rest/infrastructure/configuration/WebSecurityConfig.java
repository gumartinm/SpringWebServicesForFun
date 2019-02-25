package de.spring.webservices.rest.infrastructure.configuration;

// import org.springframework.boot.actuate.autoconfigure.web.server.ManagementServerProperties;
// import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.header.writers.HstsHeaderWriter;
import org.springframework.security.web.util.matcher.AnyRequestMatcher;

@EnableWebSecurity
// @Order because I want the default security settings for the actuator endpoints
// No working on Spring Boot 2 @Order(ManagementServerProperties.BASIC_AUTH_ORDER + 1)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    http.csrf().disable();

    HstsHeaderWriter writer = new HstsHeaderWriter(true);
    writer.setRequestMatcher(AnyRequestMatcher.INSTANCE);
    http.headers().addHeaderWriter(writer);
  }

}

