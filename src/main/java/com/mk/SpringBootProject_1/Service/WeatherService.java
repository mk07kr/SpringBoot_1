package com.mk.SpringBootProject_1.Service;

import com.mk.SpringBootProject_1.Api_response.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class WeatherService {
    // Value Annotation for fetch api key from application properties
    @Value("${weather.api.key}")
    private  String apiKey ;
    private final static String API = "http://api.weatherapi.com/v1/current.json?key=API_KEY&q=CITY&aqi=no";
    @Autowired
    private RestTemplate restTemplate;

    public Weather getWeather(String city) {
        String finalApi = API.replace("API_KEY", apiKey).replace("CITY", city);
        ResponseEntity<Weather> response = restTemplate.exchange(finalApi, HttpMethod.GET, null, Weather.class);
        return response.getBody();
    }
}
