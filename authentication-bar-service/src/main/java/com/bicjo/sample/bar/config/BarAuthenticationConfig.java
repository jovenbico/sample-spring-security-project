package com.bicjo.sample.bar.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;

@Configuration
public class BarAuthenticationConfig {

	@Bean
	public UserDetailsService barUserDetailsService() {
		UserDetailsManager manager = new InMemoryUserDetailsManager();
		manager.createUser(User.withUsername("bar").password("pass").roles("USER").build());
		return manager;
	}

	@Component("barAuthenticationProvider")
	public static class BarAuthenticationProvider implements AuthenticationProvider {

		@Autowired
		@Qualifier("barUserDetailsService")
		private UserDetailsService userDetailsService;

		@Override
		public Authentication authenticate(Authentication authentication) throws AuthenticationException {
			String username = String.valueOf(authentication.getPrincipal());
			String password = String.valueOf(authentication.getCredentials());

			UserDetails userDetails = null;

			try {

				userDetails = userDetailsService.loadUserByUsername(username);

				if (!userDetails.getPassword().equals(password)) {
					throw new BadCredentialsException(password);
				}

			} catch (UsernameNotFoundException ex) {
				throw ex;
			}

			return new UsernamePasswordAuthenticationToken(//
					authentication.getPrincipal(), //
					authentication.getCredentials(), //
					userDetails.getAuthorities()//
			);
		}

		@Override
		public boolean supports(Class<?> authentication) {
			return UsernamePasswordAuthenticationToken.class//
					.isAssignableFrom(authentication);
		}

	}

}
