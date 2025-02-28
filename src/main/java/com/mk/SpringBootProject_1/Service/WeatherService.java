package com.mk.SpringBootProject_1.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mk.SpringBootProject_1.Api_response.Weather;
import com.mk.SpringBootProject_1.Cache.AppCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
@Slf4j
public class WeatherService {
    // Value Annotation for fetch api key from application properties
    @Value("${weather.api.key}")
    private  String apiKey ;

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private AppCache Cache;
@Autowired
private RedisService redisService;

    public Weather getWeather(String city)  {
        try {
            Weather weatherResponse = redisService.get(city, Weather.class);
            if (weatherResponse != null) {
                return weatherResponse;
            }
            else{
                String finalApi = Cache.appCache.get(AppCache.keys.WEATHER_API.toString()).replace("<apiKey>", apiKey).replace("<CITY>", city);
                ResponseEntity<Weather> response = restTemplate.exchange(finalApi, HttpMethod.GET, null, Weather.class);
                Weather body = response.getBody();
                if(body != null) {
                    redisService.set(city,body,300l);
                }
                return body;
            }

        } catch (Exception e) {
            log.error("Error getting weather from cache",e);
        }
return null;
    }
}
