package com.springcloud.sessionexample.authredisbased;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.csrf.CsrfFilter;

import com.springcloud.sessionexample.authredisbased.rest.RESTAuthenticationEntryPoint;
import com.springcloud.sessionexample.authredisbased.rest.RESTAuthenticationFailureHandler;
import com.springcloud.sessionexample.authredisbased.rest.RESTAuthenticationSuccessHandler;

/**
 * Spring security configuration
 * 
 * @author a525125
 *
 */
@Configuration
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private CsrfService csrfService;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private RESTAuthenticationEntryPoint authenticationEntryPoint;
	@Autowired
	private RESTAuthenticationFailureHandler authenticationFailureHandler;
	@Autowired
	private RESTAuthenticationSuccessHandler authenticationSuccessHandler;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.anyRequest()
				.permitAll()
				.and()
				.httpBasic()
				.and()
				.csrf()
				.csrfTokenRepository(csrfService.csrfTokenRepository())
				.and()
				.addFilterAfter(csrfService.csrfHeaderFilter(),
						CsrfFilter.class);

		http.exceptionHandling().authenticationEntryPoint(
				authenticationEntryPoint);
		http.formLogin().successHandler(authenticationSuccessHandler);
		http.formLogin().failureHandler(authenticationFailureHandler);

	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		super.configure(web);
	}
}
