package com.flyone.weather.service;

import com.flyone.weather.vo.Weather;

/**
 * Created by jack on 2018/2/21.
 */
public interface WeatherReportService {
    Weather getDataByCityId(String cityId);
}
