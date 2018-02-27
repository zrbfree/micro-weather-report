/********************************************************** 
* @Title: IWeaterReportService.java  
* @Package com.example.demo.service  
* @Description: 
* @author RICK  
* @date 2018年2月27日 下午3:29:56
* @version V1.0  
***********************************************************/  
package com.example.demo.service;

import com.example.demo.vo.Weather;

public interface IWeaterReportService {
	
	
	/**  
	* @Description: 根据城市ID查询天气信息
	* @param cityId
	* @return  
	* @return: Weather
	* @author: RICK
	* @date: 2018年2月27日 下午3:30:37
	*/ 
	Weather getDataByCityId(String cityId);
}
