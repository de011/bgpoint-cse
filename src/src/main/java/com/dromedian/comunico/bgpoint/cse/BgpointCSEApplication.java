package com.dromedian.comunico.bgpoint.cse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.dromedian.comunico.bgpoint.cse.audit.CSEAuditorAwareImpl;

/**
*
* @author Waseem
*/

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
