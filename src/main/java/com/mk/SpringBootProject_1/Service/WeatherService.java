
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import src.main.java.com.mk.SpringBootProject_1.Api_response.Weather;

// package main.java.com.mk.SpringBootProject_1.Service;
 
@Component
public class WeatherService {
    private final String apiKey="bb903c0a03324a75a6f181329251702";
    private final String API="http://api.weatherapi.com/v1/current.json?key=API_KEY&q=CITY&aqi=no";

    @Autowired
    private RestTemplate restTemplate;

    public ResponseEntity<?> getWeather(String city){
    String finalApi= API.replace("API_KEY",apiKey).replace("CITY", city);
    ResponseEntity<Weather> response=restTemplate.exchange(finalApi,HttpMethod.GET,null,Weather.class);
    Weather body=response.getBody();
    return body;
    }
}
