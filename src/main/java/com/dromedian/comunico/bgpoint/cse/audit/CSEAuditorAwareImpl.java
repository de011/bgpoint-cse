package com.dromedian.comunico.bgpoint.cse.audit;

import org.springframework.data.domain.AuditorAware;

/**
 * @author DEEPAK
 *
 */
public class CSEAuditorAwareImpl implements AuditorAware<String> {
	@Override
	public String getCurrentAuditor() {
		return " ";
	}	
}

