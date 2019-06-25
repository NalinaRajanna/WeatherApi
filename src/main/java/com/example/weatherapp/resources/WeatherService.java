package com.example.weatherapp.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.weatherapp.error.ErrorDetails;
import com.example.weatherapp.exception.RestClientApplicationException;
import com.weatherlibrary.datamodel.WeatherModel;

import reactor.core.publisher.Mono;

@Component
public class WeatherService {

	@Value("${apixu.user.key}")
	private String apixuKey;

	@Value("${apixu.base.current.weather.url}")
	private String apixuCurrentWeatherBaseUrl;

	@Value("${apixu.base.forecast.weather.url}")
	private String apixuForecastWeatherBaseUrl;

	@Autowired
	private WebClient.Builder webClientBuilder;

	public Object getWeatherData(String city, String type) throws RestClientApplicationException {
		String uriString;
		if (type.equals("Current")) {
			uriString = apixuCurrentWeatherBaseUrl + "key=" + apixuKey + "&q=" + city;
		} else if (type.equals("Forecast")) {
			uriString = apixuForecastWeatherBaseUrl + "key=" + apixuKey + "&q=" + city + "&days=7";

		} else {
			throw new RestClientApplicationException("Type value should be either Current or forecast", null);
		}

		return getWeather(uriString);
	}

	public Object getWeather(String uriString) {
		return webClientBuilder.build().get().uri(uriString).exchange().flatMap(clientResponse -> {
			if (clientResponse.statusCode().isError()) {
				return clientResponse.bodyToMono(ErrorDetails.class);
			} else {
				return clientResponse.bodyToMono(WeatherModel.class);

			}

		}).block();
	}

}
