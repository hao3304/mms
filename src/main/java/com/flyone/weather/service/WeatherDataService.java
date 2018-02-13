package com.flyone.weather.service;

import com.flyone.weather.vo.WeatherResponse;
import org.springframework.stereotype.Service;

public interface WeatherDataService {
    WeatherResponse getDataByCityId(String cityId);
    WeatherResponse getDataByCityName(String cityName);
}
