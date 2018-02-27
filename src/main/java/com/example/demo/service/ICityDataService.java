/********************************************************** 
* @Title: ICityDataService.java  
* @Package com.example.demo.service  
* @Description: 
* @author RICK  
* @date 2018年2月27日 下午2:45:06
* @version V1.0  
***********************************************************/  
package com.example.demo.service;

import java.util.List;

import com.example.demo.vo.City;

public interface ICityDataService {
	
	/**
	* @Description: 获取城市数据
	* @return
	* @throws Exception  
	* @return: List<City>
	* @author: RICK
	* @date: 2018年2月27日 下午2:47:55
	 */
	List<City> listCity() throws Exception;
}
