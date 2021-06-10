package com.spring.jpa;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;


@Configuration
//@EnableWebMvc //this will tell instead xml we r gonna use java based configuration. This will be ony used to specify for webMVC not in spring core applications
@ComponentScan(basePackages = { "com.spring.jpa.*" })
public class WebConfigFile {

	@Bean 
	public DataSource getDataSource() { 
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/productdb");
		dataSource.setUsername("root"); dataSource.setPassword("Welcome@123");

		return dataSource; 
	}

	@Bean(name="entityManagerFactory")
	public LocalSessionFactoryBean sessionFactory() {
	    LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
	    sessionFactory.setDataSource(getDataSource());
	    return sessionFactory;
	} 

	/*
	 * @Bean("p1") public ProductDAO getProductDAO() { return new
	 * ProductDAO("Testing"); }
	 */
	
	/*
	 * @Bean public InternalResourceViewResolver getResolved() {
	 * InternalResourceViewResolver irvr = new InternalResourceViewResolver();
	 * irvr.setPrefix("/WEB-INF/views/"); irvr.setSuffix(".jsp"); return irvr; }
	 */
}
