package com.alexander.weatherapi.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "openweather")
public record OpenWeatherProperties(
        String apiKey,
        String baseUrl
) {
}
