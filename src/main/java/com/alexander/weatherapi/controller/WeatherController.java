package com.alexander.weatherapi.controller;

import com.alexander.weatherapi.dto.WeatherResponse;
import com.alexander.weatherapi.service.WeatherService;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/api/weather")
    public WeatherResponse getWeather(
            @RequestParam
            @Size(min = 2, max = 100)
            @Pattern(regexp = "^[a-zA-Z\\\\s-]+$")
            String city
    ) {
        return weatherService.getCurrentWeather(city);
    }
}
