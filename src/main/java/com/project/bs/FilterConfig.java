package com.project.bs;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

	@Bean
	public FilterRegistrationBean<DoFilter> filterRegistrationBean() {
		FilterRegistrationBean<DoFilter> registrationBean = new FilterRegistrationBean<DoFilter>();
		DoFilter doFilter = new DoFilter();

		registrationBean.setFilter(doFilter);
		registrationBean.addUrlPatterns("/admin");
		registrationBean.setOrder(2); // set precedence
		return registrationBean;
	}
}