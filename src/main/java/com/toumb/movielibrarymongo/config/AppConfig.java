package com.toumb.movielibrarymongo.config;

import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages="com.toumb.employeeappspring")
@PropertySource("classpath:application.properties")
public class AppConfig {
	//Set up a variable to hold the properties
	@Autowired
	private Environment env;
	
	// Set up a Logger for diagnostics
	private Logger logger = Logger.getLogger(getClass().getName());
	
	// Define a bean for the security datasource
	@Bean
	public DataSource securityDataSource() {
		// Create a connection pool
		ComboPooledDataSource securityDataSource = new ComboPooledDataSource();
		
		// Log the connection properties
		logger.info(">>> jdbc.url=" + env.getProperty("spring.datasource.url"));
		logger.info(">>> jdbc.user=" + env.getProperty("spring.datasource.username"));
		
		// Set the database connection properties
		securityDataSource.setJdbcUrl(env.getProperty("spring.datasource.url"));
		securityDataSource.setUser(env.getProperty("spring.datasource.username"));
		securityDataSource.setPassword(env.getProperty("spring.datasource.password"));
		
		// Set connection pool properties
		securityDataSource.setInitialPoolSize(getIntProperty("connection.pool.initialPoolSize"));
		securityDataSource.setMinPoolSize(getIntProperty("connection.pool.minPoolSize"));
		securityDataSource.setMaxPoolSize(getIntProperty("connection.pool.maxPoolSize"));
		securityDataSource.setMaxIdleTime(getIntProperty("connection.pool.maxIdleTime"));
		
		return securityDataSource;
	}
	
	// Helper method to read environment property and convert it to int
	private int getIntProperty(String propertyName) {
		String propertyValue = env.getProperty(propertyName);
		
		int intPropertyValue = Integer.parseInt(propertyValue);
		
		return intPropertyValue;
		
	}
	
}
