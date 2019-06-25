package com.example.weatherapp;


import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.example.weatherapp.exception.RestClientApplicationException;
import com.example.weatherapp.resources.WeatherService;


@WebAppConfiguration
@RunWith(MockitoJUnitRunner.class)
public class WeatherServiceTest {

    @InjectMocks
    @Autowired
    WeatherService weatherService;
    
    @Autowired
    private WebTestClient webTestClient;
    
    
    @Mock
    RestClientApplicationException restClientException;
    
    
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    
   
    
    @Test
    public void WhenTypeNotCurrentOrForecast()throws Exception {
    	thrown.expect(RestClientApplicationException.class);
    	thrown.expectMessage("Type value should be either Current or forecast");
    	weatherService.getWeatherData("Paris","vsdfjhsd");
    }
    
    
    
    

       
    
    
}
