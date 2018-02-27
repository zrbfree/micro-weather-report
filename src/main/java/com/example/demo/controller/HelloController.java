/********************************************************** 
* @Title: HelloController.java  
* @Package com.example.demo.controller  
* @Description: hello 控制器
* @author RICK  
* @date 2018年2月26日 下午1:20:08
* @version V1.0  
***********************************************************/  
package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	@GetMapping("/hello")
	public String helloWorld(String a) {
		return "Hello World!";
	}

}
