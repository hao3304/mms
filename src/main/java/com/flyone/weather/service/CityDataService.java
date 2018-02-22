package com.flyone.weather.service;

import com.flyone.weather.vo.City;

import java.util.List;

/**
 * Created by jack on 2018/2/21.
 */
public interface CityDataService {
    List<City> listCity() throws Exception;
}
