package com.synergisticit.config;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@PropertySource(value = {"classpath:db.properties"})
@ComponentScan(basePackages = {"com.synergisticit"})
public class AppConfig implements WebMvcConfigurer {

	private Environment env;
	
	@Autowired
	public AppConfig(Environment env) {
		this.env = env;
	}
	
	@Bean
	public DataSource dataSource() {
		log.debug("AppConfig.dataSource()......");
		
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl(env.getProperty("url"));
		dataSource.setDriverClassName(env.getProperty("driver"));
		dataSource.setUsername(env.getProperty("dbUsername"));
		dataSource.setPassword(env.getProperty("dbPassword"));
		
		return dataSource;
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		log.debug("AppConfig.entityManagerFactory()......");
		
		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactory.setDataSource(dataSource());
		entityManagerFactory.setPackagesToScan("com.synergisticit.domain");
		entityManagerFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		entityManagerFactory.setJpaProperties(jpaProperties());
		
		return entityManagerFactory;
	}
	
	private Properties jpaProperties() {
		
		Properties jpaProperties = new Properties();
		jpaProperties.setProperty("hibernate.hbm2ddl.auto", "update");
		jpaProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		jpaProperties.setProperty("hibernate.show_SQL", "true");
		
		return jpaProperties;
	}
	
	@Bean
	public JpaTransactionManager transactionManager() {
		log.debug("AppConfig.transactionManager()......");
		
		JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
		jpaTransactionManager.setDataSource(dataSource());
		
		return jpaTransactionManager;
	}
	
	@Bean
	public InternalResourceViewResolver internalResourceViewResolver() {
		log.debug("AppConfig.internalResourceViewResolver()......");
		
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		viewResolver.setViewClass(JstlView.class);
		
		return viewResolver;
	}
	
//	@Bean
//	public RestTemplate restTemplate() {
//		
//		List<HttpMessageConverter<?>> messageConverter = new ArrayList<>();
//		messageConverter.add(new StringHttpMessageConverter());
//		messageConverter.add(new MappingJackson2XmlHttpMessageConverter());
//		messageConverter.add(new MappingJackson2HttpMessageConverter());
//		
//		RestTemplate restTemplate = new RestTemplate();
//		restTemplate.setMessageConverters(messageConverter);
//		restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor("admin", "admin", Charset.forName("UTF-8")));
//		
//		return restTemplate;
//	}
}
