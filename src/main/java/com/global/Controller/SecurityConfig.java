package com.global.Controller;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfiguration {
	@SuppressWarnings({ "removal", "deprecation" })
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeHttpRequests().requestMatchers("/api/**").authenticated().and().httpBasic();
	}

}