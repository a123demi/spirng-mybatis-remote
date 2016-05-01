package com.lm.core.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContextUtil {
	private static ApplicationContext ctx;
	
	private ApplicationContextUtil(){}
	public static  ApplicationContext getInstance(){
		if(null == ctx){
			ctx = new ClassPathXmlApplicationContext(
					"classpath:application-context.xml");
		}
		return ctx;
		
	}
}
