package com.alexander.weatherapi.client;

import com.alexander.weatherapi.configuration.OpenWeatherProperties;
import com.alexander.weatherapi.dto.OpenWeatherResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class OpenWeatherClient {

    private final RestClient restClient;
    private final OpenWeatherProperties properties;

    public OpenWeatherClient(OpenWeatherProperties properties) {
        this.properties = properties;
        this.restClient = RestClient.builder()
                .baseUrl(properties.baseUrl())
                .build();
    }

    public OpenWeatherResponse getCurrentWeather(String city) {
        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/weather")
                        .queryParam("q", city)
                        .queryParam("appid", properties.apiKey())
                        .queryParam("units", "metric")
                        .build()
                )
                .retrieve()
                .body(OpenWeatherResponse.class);
    }
}
