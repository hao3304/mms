package com.flyone.weather.job;

import com.flyone.weather.service.CityDataService;
import com.flyone.weather.service.WeatherDataService;
import com.flyone.weather.vo.City;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.List;

public class WeatherDataSyncJob extends QuartzJobBean {

    private final static Logger logger = LoggerFactory.getLogger(WeatherDataSyncJob.class);

    @Autowired
    private CityDataService cityDataService;

    @Autowired
    private WeatherDataService weatherDataService;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        logger.info("Weather Data Sync Job");

        List<City> cityList = null;

        try {
            cityList = cityDataService.listCity();
        } catch (Exception e) {
            logger.error("Exception!", e);
        }

        for(City city : cityList){
            String cityId = city.getCityId();
            logger.info("Weather Data Sync Job, CityId: " + cityId);
            weatherDataService.syncDataByCityId(cityId);
        }
    }
}
