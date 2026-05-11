package com.alexander.weatherapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record WeatherResponse(
        String condition,
        double temperature,

        @JsonProperty("wind_speed")
        double windSpeed
) {
}
