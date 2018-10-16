package de.spring.example.clients;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import de.spring.example.clients.service.AdClientService;

@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Bean
  CommandLineRunner lookup(AdClientService example) {
    return args -> {

            example.findAll().map(ad -> {
				System.out.println(ad.getCreatedAt());
                return ad;
            });

    };
  }

}
