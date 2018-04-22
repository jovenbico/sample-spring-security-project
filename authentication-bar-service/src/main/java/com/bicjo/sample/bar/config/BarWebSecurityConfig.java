package com.bicjo.sample.bar.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@Order(1)
@ConditionalOnProperty(prefix = "example.bar.authentication", name = "enabled", havingValue = "true", matchIfMissing = false)
public class BarWebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("barAuthenticationProvider")
	private AuthenticationProvider authenticationProvider;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.antMatcher("/bar/**")//
				.authorizeRequests()//
				.anyRequest().authenticated()//
				.and()//
				.formLogin()//
				.loginPage("/bar/login")//
				.loginProcessingUrl("/bar/login")//
				.permitAll()//
		;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider);
	}
}
