package de.example.spring.pact.consumer.infrastructure.feign.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import de.example.spring.pact.consumer.domain.entity.Car;

@FeignClient("web-services-spring-pact-server")
public interface CarFeignClient {

    @GetMapping("/cars/")
	List<Car> findAll();
}
