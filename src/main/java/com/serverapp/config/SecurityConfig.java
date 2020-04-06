package com.serverapp.config;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.serverapp.security.JwtAuthenticationEntryPoint;
import com.serverapp.security.JwtAuthenticationFilter;
import com.serverapp.security.JwtAuthenticationProvider;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Value("${security.static.urls:}")
	private String[] staticUrls;
	
	@Value("${security.enabled: false}")
	private boolean securityEnabled;
	
	@Autowired
	private JwtAuthenticationProvider authenticationProvider;
	
	@Autowired
	private JwtAuthenticationFilter authenticationFilter;
	
	@Autowired
	private JwtAuthenticationEntryPoint entryPoint;
	
	@Bean
	public AuthenticationManager authenticationManager() {
		return new ProviderManager(Collections.singletonList(authenticationProvider));
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable();
		http.headers().cacheControl().disable().and().headers().frameOptions().disable();
		http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
		
		if(securityEnabled) {
			
			http.authorizeRequests()
			.antMatchers("/authenticate", "/signup").permitAll()
			.and()
			.exceptionHandling().authenticationEntryPoint(entryPoint)
			.and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.authorizeRequests().antMatchers(HttpMethod.GET, staticUrls).permitAll()
			.anyRequest().authenticated();
		}else {
			http.authorizeRequests().antMatchers("/").permitAll();
		}
	}
}
