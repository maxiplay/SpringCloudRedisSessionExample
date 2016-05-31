package com.springcloud.sessionexample;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import net.bull.javamelody.MonitoringFilter;
import net.bull.javamelody.Parameter;
import net.bull.javamelody.SessionListener;

/**
 * Java melody configuration
 * 
 * @author a525125
 *
 */
@Configuration
@ImportResource("classpath:net/bull/javamelody/monitoring-spring-aspectj.xml")
public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(GatewayApplication.class);
	}

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		servletContext.addListener(new SessionListener());
		super.onStartup(servletContext);
	}

	@Bean
	public FilterRegistrationBean javaMelody() {
		FilterRegistrationBean javaMelody = new FilterRegistrationBean();
		javaMelody.setFilter(new MonitoringFilter());
		javaMelody.setName("monitoring");

		// see the list of parameters:
		// https://github.com/javamelody/javamelody/wiki/UserGuide#6-optional-parameters
		javaMelody.addInitParameter(Parameter.LOG.getCode(), Boolean.toString(true));

		javaMelody.addUrlPatterns("/*");
		return javaMelody;
	}

}
