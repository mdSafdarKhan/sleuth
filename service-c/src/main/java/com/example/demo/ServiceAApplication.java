package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@RestController
@SpringBootApplication
public class ServiceAApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceAApplication.class, args);
	}

}
@RestController
class MyController{

	@Autowired
	RestTemplate restTemplate;

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	private static final Logger LOG = LoggerFactory.getLogger(MyController.class.getName());

	@GetMapping(value="/zipkin3")
	public String zipkinService3(){
		LOG.info("Inside zipkinService 3..");
		String response = (String) restTemplate.exchange("http://localhost:8084/zipkin4", HttpMethod.GET, null, new ParameterizedTypeReference<String>() {}).getBody();
		return "Hi...";
	}
}