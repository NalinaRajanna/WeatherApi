package com.example.weatherapp.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.weatherapp.error.Error;
import com.example.weatherapp.exception.RestClientApplicationException;

@RestController
@RequestMapping("/api/weather")
public class WeatherApiResource {

	@Autowired
	private WeatherService weatherService;

	@RequestMapping(method = { RequestMethod.GET }, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getWeatherForecast(@RequestParam(value = "city", required = true) String city,
			@RequestParam(value = "type", required = true) String type) {

		try {
			return ResponseEntity.status(HttpStatus.OK).body(weatherService.getWeatherData(city, type));

		} catch (RestClientApplicationException exception) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(createError(exception));
		}

	}
	
	private Error createError(RestClientApplicationException e) {
		com.example.weatherapp.error.Error error = new com.example.weatherapp.error.Error();
		error.setMessage(e.getMessage());
		return error;
	}

}
