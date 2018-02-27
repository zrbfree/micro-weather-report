/********************************************************** 
* @Title: WeatherDataServiceImpl.java  
* @Package com.example.demo.service  
* @Description: 
* @author RICK  
* @date 2018年2月27日 上午10:47:14
* @version V1.0  
***********************************************************/  
package com.example.demo.service;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.vo.WeatherResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class WeatherDataServiceImpl implements IWeatherDataService {
	
	private final static Logger logger = LoggerFactory.getLogger(WeatherDataServiceImpl.class);
	
	private static final String WEATHER_URI = "http://wthrcdn.etouch.cn/weather_mini?";

	private static final long TIME_OUT = 1800L; //redis缓存数据超时时间 1800s(30分钟)
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Override
	public WeatherResponse getDataByCityId(String cityId) {
		String uri = WEATHER_URI + "citykey=" + cityId;
		return this.doGetWeahter(uri);
	}

	@Override
	public WeatherResponse getDataByCityName(String cityName) {
		String uri = WEATHER_URI + "city=" + cityName;
		return this.doGetWeahter(uri);
	}
	
	private WeatherResponse doGetWeahter(String uri) {
		String key = uri;
		String strBody = null;
		WeatherResponse resp = null;
		ObjectMapper mapper = new ObjectMapper();
		ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
		//先查询缓存，有就取数据
		if(stringRedisTemplate.hasKey(key)) {
			logger.info("Reids has data!");
			strBody = ops.get(key);
		} else {
			logger.info("Reids don't has data!");
			//没有调用第三方接口获取数据
			ResponseEntity<String> respString = restTemplate.getForEntity(uri, String.class);
			
			if (respString.getStatusCodeValue() == 200) {
				strBody = respString.getBody();
			}
			
			//数据写入缓存
			ops.set(key, strBody, TIME_OUT, TimeUnit.SECONDS);
		}

		try {
			resp = mapper.readValue(strBody, WeatherResponse.class);
		} catch (IOException e) {
//			e.printStackTrace();
			logger.error("error " + e);
		}
		
		return resp;
	}

	@Override
	public void syncDataByCityId(String cityId) {
		String uri = WEATHER_URI + "citykey=" + cityId;
		this.saveWeatherData(uri);
	}
	
	/**
	* @Description: 把天气数据放到缓存中
	* @param uri  
	* @return: void
	* @author: RICK
	* @date: 2018年2月27日 下午2:57:23
	 */
	private void saveWeatherData(String uri) {
		String key = uri;
		String strBody = null;
		ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
		// 没有调用第三方接口获取数据
		ResponseEntity<String> respString = restTemplate.getForEntity(uri, String.class);

		if (respString.getStatusCodeValue() == 200) {
			strBody = respString.getBody();
		}
		// 数据写入缓存
		ops.set(key, strBody, TIME_OUT, TimeUnit.SECONDS);
	}

}
