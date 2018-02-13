package com.flyone.weather.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flyone.weather.vo.WeatherResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Service
public class WeatherDataServiceImpl implements WeatherDataService {

    private static final String WEATHER_URI = "http://wthrcdn.etouch.cn/weather_mini";
    private static final Logger logger = LoggerFactory.getLogger(WeatherDataServiceImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public WeatherResponse getDataByCityId(String cityId) {
        String uri = WEATHER_URI + "?citykey=" + cityId;
        return  getWeatherByUri(uri);
    }

    @Override
    public WeatherResponse getDataByCityName(String cityName) {
        String uri = WEATHER_URI + "?city=" + cityName;
        return  getWeatherByUri(uri);
    }

    private WeatherResponse getWeatherByUri(String uri) {
        WeatherResponse resp = null;
        String strBody = null;
        ObjectMapper mapper = new ObjectMapper();
        ValueOperations<String,String> ops = stringRedisTemplate.opsForValue();

        if(stringRedisTemplate.hasKey(uri)) {
            logger.info("Redis has data");
            strBody = ops.get(uri);
        }else{
            logger.info("Redis don't has data");
            ResponseEntity<String> repStr = restTemplate.getForEntity(uri, String.class);
            if(repStr.getStatusCodeValue() == 200) {
                strBody = repStr.getBody();
                ops.set(uri, strBody, 10, TimeUnit.SECONDS);
            }

        }

        try {
            resp = mapper.readValue(strBody, WeatherResponse.class);
        } catch (IOException e) {
//            e.printStackTrace();
            logger.error("Error",e);
        }

        return  resp;
    }
}
