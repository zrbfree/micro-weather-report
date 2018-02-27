/********************************************************** 
* @Title: WeatherDataSyncJob.java  
* @Package com.example.demo.job  
* @Description: 
* @author RICK  
* @date 2018年2月27日 下午1:32:29
* @version V1.0  
***********************************************************/  
package com.example.demo.job;

import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.example.demo.service.ICityDataService;
import com.example.demo.service.IWeatherDataService;
import com.example.demo.service.WeatherDataServiceImpl;
import com.example.demo.vo.City;

public class WeatherDataSyncJob extends QuartzJobBean{
	
	private static final Logger logger = LoggerFactory.getLogger(WeatherDataSyncJob.class);
	
	@Autowired
	private ICityDataService cityDataService;
	
	@Autowired
	private IWeatherDataService weatherDataService;

	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		logger.info("---Weather Data Sync Job Start--");
		//获取城市列表
		List<City> cityList = null;
		try {
			cityList = cityDataService.listCity();
		} catch (Exception e) {
			logger.error("Exception!", e);
		}
		
		//遍历城市ID获取天气
		for (City city : cityList) {
			String cityId = city.getCityId();
			logger.info("Weather Data Sync Job, cityId :" + cityId);
			weatherDataService.syncDataByCityId(cityId);
		}
		logger.info("---Weather Data Sync Job End--");
	}

	
}
