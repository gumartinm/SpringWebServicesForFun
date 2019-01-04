package de.example.spring.pact.infrastructure.repository;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import de.example.spring.pact.domain.entity.Car;

@FeignClient("web-services-spring-pact-server")
public interface CarRepository {

    @GetMapping("/cars/")
	List<Car> findAll();
}