/********************************************************** 
* @Title: XmlBuilder.java  
* @Package com.example.demo.utils  
* @Description: 
* @author RICK  
* @date 2018年2月27日 下午2:35:30
* @version V1.0  
***********************************************************/  
package com.example.demo.utils;

import static org.hamcrest.CoreMatchers.nullValue;

import java.io.Reader;
import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import net.bytebuddy.asm.Advice.This;

public class XmlBuilder {
	
	/**
	* @Description: 将xml转换成制定的POJO
	* @param clazz
	* @param xmlStr
	* @return
	* @throws Exception  
	* @return: Object
	* @author: RICK
	* @date: 2018年2月27日 下午2:36:54
	 */
	public static Object xmlStrToObject(Class<?> clazz, String xmlStr) throws Exception{
		Object xmlObject = null;
		Reader reader = null;
		JAXBContext context = JAXBContext.newInstance(clazz);
		//Xml转换为对象的接口
		Unmarshaller unmarshaller = context.createUnmarshaller();
		reader = new StringReader(xmlStr);
		xmlObject = unmarshaller.unmarshal(reader);
		if (null != reader) {
			reader.close();
		}
		return xmlObject;
	}

}
