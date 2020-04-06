package com.serverapp.config;

import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class JPAAuditConfig {

	@Bean
	public AuditorAware<String> auditorProvider(){
		return new AuditorAware<String>() {
			
			@Override
			public Optional<String> getCurrentAuditor() {
				return Optional.of("admin");
			}
		};
	}
}
