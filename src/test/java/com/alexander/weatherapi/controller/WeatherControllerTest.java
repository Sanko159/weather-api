package com.alexander.weatherapi.controller;

import com.alexander.weatherapi.dto.WeatherResponse;
import com.alexander.weatherapi.service.WeatherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WeatherController.class)
class WeatherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private WeatherService weatherService;

    @Test
    void shouldReturnWeatherResponse() throws Exception {

        when(weatherService.getCurrentWeather("Bratislava"))
                .thenReturn(new WeatherResponse(
                        "few clouds",
                        22.81,
                        12.96
                ));

        mockMvc.perform(get("/api/weather")
                        .param("city", "Bratislava"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.condition").value("few clouds"))
                .andExpect(jsonPath("$.temperature").value(22.81))
                .andExpect(jsonPath("$.wind_speed").value(12.96));
    }

    @Test
    void shouldReturnBadRequestForInvalidCity() throws Exception {

        mockMvc.perform(get("/api/weather")
                        .param("city", "Bratislava123"))
                .andExpect(status().isBadRequest());
    }
}
