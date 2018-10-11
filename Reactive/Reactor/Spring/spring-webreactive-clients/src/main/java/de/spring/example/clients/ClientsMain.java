package de.spring.example.clients;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import de.spring.example.clients.service.AdClientService;
import de.spring.example.context.UsernameContext;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

public class ClientsMain {
	private static final String USERNAME = "SnakeEyes";

	public static void main(String[] args) {
		AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext();

		AdClientService example = (AdClientService) appContext.getBean("adClientService");

		Mono.subscriberContext().map(context -> {
			Context updatedContext = context;
			if (!context.hasKey(UsernameContext.class)) {
				updatedContext = context.put(UsernameContext.class, new UsernameContext(USERNAME));
			}
			return updatedContext;
		});

		example.findAll().map(ad -> {
			System.out.println(ad.getAdDescriptions());
			return ad;
		});
	}

}
