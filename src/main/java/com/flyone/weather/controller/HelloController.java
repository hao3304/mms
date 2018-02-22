package com.flyone.weather.controller;

import com.flyone.weather.service.WeatherDataService;
import com.flyone.weather.vo.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private WeatherDataService weatherDataService;

    @GetMapping("/templates/weather/name/{name}")
    public WeatherResponse getWeather(@PathVariable("name") String name) {
        return weatherDataService.getDataByCityName(name);
    }

    @GetMapping("/templates/weather/id/{id}")
    public WeatherResponse getWeatherById(@PathVariable("id") String id) {
        return weatherDataService.getDataByCityId(id);
    }

}
