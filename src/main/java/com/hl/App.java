package com.hl;

import org.apache.log4j.Logger;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@MapperScan("com.hl.mapper")
//@ComponentScan
//@Configuration
public class App {

	private static Logger logger = Logger.getLogger(App.class);

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
		logger.info("SpringBoot Start Success");
	}

	/*@Bean
	public ServletRegistrationBean jerseyServlet() {
		ServletRegistrationBean registrationBean = new ServletRegistrationBean(new ServletContainer());
		registrationBean.addInitParameter(ServletProperties.JAXRS_APPLICATION_CLASS, JerseyConfig.class.getName());
		return registrationBean;
	}*/
	
	

}
