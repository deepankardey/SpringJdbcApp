package com.imcs.jdbc.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;


@Component
@Configuration
@PropertySource("classpath:db.properties")
@ComponentScan("com.imcs")
public class AppConfig {
	
	@Autowired
	Environment env;
	
	@Bean
	public Connection createConnection() {
		Connection con = null;
		String driverClass = null;
		String url = null;
		String username = null;
		String pwd = null;
		try{
			driverClass = env.getProperty("driver");
			url = env.getProperty("url");
			username = env.getProperty("username");
			pwd = env.getProperty("pwd");
			Class.forName(driverClass);
			con = DriverManager.getConnection(url,username,pwd);
		}catch(Exception e){
			e.printStackTrace();
			System.out.println(e.getMessage());
		}  
		return con;
    }
	
	@Bean
	public DateFormat createDateFormat() {
		return new SimpleDateFormat(env.getProperty("dateFormat"));
	}
	
}
