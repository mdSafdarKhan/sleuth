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

	@GetMapping(value="/zipkin2")
	public String zipkinService2(){
		LOG.info("Inside zipkinService 2..");
		try{
			Thread.sleep(1000*5);
		}
		catch (java.lang.Exception e){

		}
		String response = (String) restTemplate.exchange("http://localhost:8083/zipkin3", HttpMethod.GET, null, new ParameterizedTypeReference<String>() {}).getBody();
		return "Hi...";
	}
}
