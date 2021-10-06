package com.dromedian.comunico.bgpoint.cse.audit;

import org.springframework.data.domain.AuditorAware;

/**
*
* @author Waseem
*/
public class CSEAuditorAwareImpl implements AuditorAware<String> {
	@Override
	public String getCurrentAuditor() {
		return " ";
	}	
}

