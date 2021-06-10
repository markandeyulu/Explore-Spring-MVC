package com.amazon.app;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.amazon.app.controller.ProductController2;
import com.amazon.app.dao.ProductDAO;


@Configuration
@EnableWebMvc //this will tell instead xml we r gonna use java based configuration. This will be ony used to specify for webMVC not in spring core applications
@ComponentScan(basePackages = { "com.amazon.app.*" })
public class WebConfigFile {



	@Bean 
	public DataSource getDataSource() { 
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/productdb");
		dataSource.setUsername("root"); dataSource.setPassword("Welcome@123");

		return dataSource; 
	}

	@Bean // this JDBC template available in Spring JDBC maven jar. Oracle Datasource only not in Maven.
	public JdbcTemplate getTemplate() { // will be defaultly taken by JdbcTemplate

		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		//jdbcTemplate.getDataSource().getConnection().setAutoCommit(false); If you need autoCommnit false. By default its true
		return jdbcTemplate;
	}

	@Bean("p1")
	//@Profile("testing")
	public ProductDAO getProductDAO1() {
		return new ProductDAO("Testing");
	}
	@Bean
	public InternalResourceViewResolver getResolved() {
		InternalResourceViewResolver irvr = new InternalResourceViewResolver();
		irvr.setPrefix("/WEB-INF/views/");
		irvr.setSuffix(".jsp");
		return irvr;
	}



	//@Bean("/testbeannamemapping*")
	@Bean
	public ProductController2 ProductController2() {
		return new ProductController2();
	}

	/*	@Bean
	public BeanNameUrlHandlerMapping getBeanNameUrlHandlerMapping() {
		return new BeanNameUrlHandlerMapping();
	}*/

	@Bean
	public SimpleUrlHandlerMapping getSimpleUrlHandlerMapping() {
		SimpleUrlHandlerMapping shm = new SimpleUrlHandlerMapping();
		Properties mappings = new Properties();
		mappings.setProperty("/marktestsimpleURL", "ProductController2");
		shm.setMappings(mappings);
		return shm;
	}
}
