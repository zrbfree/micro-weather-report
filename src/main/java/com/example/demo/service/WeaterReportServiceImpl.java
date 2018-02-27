/********************************************************** 
* @Title: WeaterReportServiceImpl.java  
* @Package com.example.demo.service  
* @Description: 
* @author RICK  
* @date 2018年2月27日 下午3:31:13
* @version V1.0  
***********************************************************/  
package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.vo.Weather;
import com.example.demo.vo.WeatherResponse;
@Service
public class WeaterReportServiceImpl implements IWeaterReportService {

	@Autowired
	private IWeatherDataService weatherDataService;
	
	@Override
	public Weather getDataByCityId(String cityId) {
		WeatherResponse resp = weatherDataService.getDataByCityId(cityId);
		return resp.getData();
	}

}
