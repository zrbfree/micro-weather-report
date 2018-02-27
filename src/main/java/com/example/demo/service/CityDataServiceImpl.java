/********************************************************** 
* @Title: CityDataServiceImpl.java  
* @Package com.example.demo.service  
* @Description: 
* @author RICK  
* @date 2018年2月27日 下午2:48:31
* @version V1.0  
***********************************************************/  
package com.example.demo.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.example.demo.utils.XmlBuilder;
import com.example.demo.vo.City;
import com.example.demo.vo.CityList;

@Service
public class CityDataServiceImpl implements ICityDataService {

	@Override
	public List<City> listCity() throws Exception {
		// 读取xml文件
		Resource resource = new ClassPathResource("citylist.xml");
		BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()));
		StringBuilder builder = new StringBuilder();
		String line = "";
		while((line = br.readLine()) != null) {
			builder.append(line);
		}
		
		br.close();
		
		//xml转换为java对象
		CityList cityList = (CityList)XmlBuilder.xmlStrToObject(CityList.class, builder.toString());
		return cityList.getCityList();
	}

}
