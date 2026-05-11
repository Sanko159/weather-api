package com.alexander.weatherapi.service;

import com.alexander.weatherapi.client.OpenWeatherClient;
import com.alexander.weatherapi.dto.OpenWeatherResponse;
import com.alexander.weatherapi.dto.WeatherResponse;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {

    private final OpenWeatherClient openWeatherClient;

    public WeatherService(OpenWeatherClient openWeatherClient) {
        this.openWeatherClient = openWeatherClient;
    }

    @Cacheable(value = "weather", key = "#city.toLowerCase()")
    public WeatherResponse getCurrentWeather(String city) {
        OpenWeatherResponse response = openWeatherClient.getCurrentWeather(city);

        String condition = response.weather().get(0).description();
        double temperature = response.main().temp();
        double windSpeedKmh = response.wind().speed() * 3.6;

        return new WeatherResponse(
                condition,
                temperature,
                windSpeedKmh
        );
    }
}
