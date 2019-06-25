package com.example.weatherapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@SpringBootApplication
public class WeatherAppApplication {
	Logger log = LoggerFactory.getLogger(WeatherAppApplication.class);
	
	@Bean
	public WebClient.Builder getWebClientBuilder(){
		return WebClient.builder()
				.filter(logRequest())
				.filter(logResponse());
		
	}

	public static void main(String[] args) {
		SpringApplication.run(WeatherAppApplication.class, args);
	}
	
	
	private ExchangeFilterFunction logRequest() {
		return ExchangeFilterFunction.ofRequestProcessor(clinetRequest -> {
			log.info("Request {} {}", clinetRequest.method(), clinetRequest.url());
			return Mono.just(clinetRequest);
		});
	}
	

	private ExchangeFilterFunction logResponse() {
		return ExchangeFilterFunction.ofResponseProcessor(clinetResponse -> {
			log.info("Response status code {} {}", clinetResponse.statusCode(), clinetResponse.rawStatusCode());
			return Mono.just(clinetResponse);
		});
	}
	
}
