/********************************************************** 
* @Title: QuartzConfiguration.java  
* @Package com.example.demo.config  
* @Description: 定时器配置
* @author RICK  
* @date 2018年2月27日 下午1:35:20
* @version V1.0  
***********************************************************/  
package com.example.demo.config;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.job.WeatherDataSyncJob;

@Configuration
public class QuartzConfiguration {
	
	private static final int TIME = 1800; //30分钟


	//JobDetail
	@Bean
	public JobDetail weatherDataSyncJobJobDetail() {
		return JobBuilder.newJob(WeatherDataSyncJob.class).withIdentity("WeatherDataSyncJob")
		.storeDurably().build();
	}
	
	
	//Trigger
	@Bean
	public Trigger weatherDataSyncTrigger() {
		SimpleScheduleBuilder schedBuilder = SimpleScheduleBuilder.simpleSchedule()
				.withIntervalInSeconds(TIME) .repeatForever();
		return TriggerBuilder.newTrigger().forJob(weatherDataSyncJobJobDetail()).withIdentity("weatherDataSyncTrigger")
				.withSchedule(schedBuilder).build();
	}

}
