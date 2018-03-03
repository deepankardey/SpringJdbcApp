package com.imcs.jdbc.context;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.imcs.jdbc.config.AppConfig;

final public class MyApplicationContext {
	private static ApplicationContext context = null;
	
	private MyApplicationContext() {
		
	}
	
	public static ApplicationContext getInstance() {
		if(context == null)
			context = getApplicationInstance();
		return context;
			
	}
	
	private static ApplicationContext getApplicationInstance() {
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		return context;
	}
}
