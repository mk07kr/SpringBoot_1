package com.mk.SpringBootProject_1.Api_response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class Weather {

private Current current;

   @Getter
   @Setter
   public class Current{

       @JsonProperty("temp_c")
        private int tempC;
        private double wind_kph;

        @JsonProperty("wind_dir")
        private String windDirection;

        private int humidity;
        private double feelslike_c;

    }
}








