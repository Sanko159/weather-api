package com.alexander.weatherapi.service;

import com.alexander.weatherapi.client.OpenWeatherClient;
import com.alexander.weatherapi.dto.OpenWeatherCondition;
import com.alexander.weatherapi.dto.OpenWeatherMain;
import com.alexander.weatherapi.dto.OpenWeatherResponse;
import com.alexander.weatherapi.dto.OpenWeatherWind;
import com.alexander.weatherapi.dto.WeatherResponse;
import org.junit.jupiter.api.Test;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class WeatherServiceTest {

    private final OpenWeatherClient openWeatherClient = mock(OpenWeatherClient.class);
    private final WeatherService weatherService = new WeatherService(openWeatherClient);

    @Test
    void shouldMapOpenWeatherResponseToWeatherResponse() {
        OpenWeatherResponse openWeatherResponse = new OpenWeatherResponse(
                List.of(new OpenWeatherCondition("few clouds")),
                new OpenWeatherMain(22.81),
                new OpenWeatherWind(3.6)
        );

        when(openWeatherClient.getCurrentWeather("Bratislava"))
                .thenReturn(openWeatherResponse);

        WeatherResponse response = weatherService.getCurrentWeather("Bratislava");

        assertEquals("few clouds", response.condition());
        assertEquals(22.81, response.temperature());
        assertEquals(12.96, response.windSpeed());
    }
}
