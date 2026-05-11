package com.alexander.weatherapi.dto;

import java.util.List;

public record OpenWeatherResponse(
        List<OpenWeatherCondition> weather,
        OpenWeatherMain main,
        OpenWeatherWind wind
) {
}
