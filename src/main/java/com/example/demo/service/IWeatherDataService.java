package com.example.demo.service;

import com.example.demo.vo.WeatherResponse;

/**
 * Weather Data Service.
 * 天气数据接口
 * @since 1.0.0 2017年11月22日
 * @author <a href="https://waylau.com">Way Lau</a> 
 */
public interface IWeatherDataService {
	/**
	 * 根据城市ID查询天气数据
	 * 
	 * @param cityId
	 * @return
	 */
	WeatherResponse getDataByCityId(String cityId);

	/**
	 * 根据城市名称查询天气数据
	 * 
	 * @param cityId
	 * @return
	 */
	WeatherResponse getDataByCityName(String cityName);
	
	/**
	* @Description: 根据城市ID来同步天气
	* @param cityId  
	* @return: void
	* @author: RICK
	* @date: 2018年2月27日 下午2:55:36
	 */
	void syncDataByCityId(String cityId);
	
}
