/********************************************************** 
* @Title: RestConfiguration.java  
* @Package com.example.demo.config  
* @Description: RestTemplate 配置类信息
* @author RICK  
* @date 2018年2月27日 上午11:07:05
* @version V1.0  
***********************************************************/  
package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfiguration {
	
	@Autowired
	private RestTemplateBuilder restTemplateBuilder;
	
	@Bean
	public RestTemplate restTemplate() {
		return this.restTemplateBuilder.build();
	}

}
