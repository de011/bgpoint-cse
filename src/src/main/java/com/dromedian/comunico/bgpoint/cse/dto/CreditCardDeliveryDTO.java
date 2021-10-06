package com.dromedian.comunico.bgpoint.cse.dto;

import java.io.Serializable;

/**
*
* @author Waseem
*/
public class CreditCardDeliveryDTO implements Serializable {
	
	private static final long serialVersionUID = 7161147197146944629L;
	private Integer id;
	private Long codicePan;

	public CreditCardDeliveryDTO() {

	}

	public CreditCardDeliveryDTO(Integer id, Long codicePan) {
		super();
		this.id = id;
		this.codicePan = codicePan;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getCodicePan() {
		return codicePan;
	}

	public void setCodicePan(Long codicePan) {
		this.codicePan = codicePan;
	}

	@Override
	public String toString() {
		return "CreditCardDeliveryDTO [id=" + id + ", codicePan=" + codicePan + "]";
	}
}
