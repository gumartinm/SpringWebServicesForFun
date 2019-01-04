package de.example.spring.pact.consumer.infrastructure.feign.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import de.example.spring.pact.consumer.infrastructure.repository.dto.CarDto;

@FeignClient("web-services-spring-pact-server")
public interface CarFeignClient {

    @GetMapping(path = "/cars/", produces = "application/json")
	List<CarDto> findAll();
}
