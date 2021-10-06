package com.dromedian.comunico.bgpoint.cse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.dromedian.comunico.bgpoint.cse.audit.CSEAuditorAwareImpl;

import ch.qos.logback.core.joran.conditional.IfAction;


/**
 * @author DEEPAK
 *
 */
@EnableCaching
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@SpringBootApplication
@EnableTransactionManagement
public class BgpointCSEApplication {
	
	@Bean
	public AuditorAware<String> auditorAware() {
		return new CSEAuditorAwareImpl();
	}
	public static void main(String[] args) {
		SpringApplication.run(BgpointCSEApplication.class, args);
	}
	
	
	
		
	}

