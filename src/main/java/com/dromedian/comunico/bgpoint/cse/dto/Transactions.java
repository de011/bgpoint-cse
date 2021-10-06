package com.dromedian.comunico.bgpoint.cse.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


/**
 * @author DEEPAK
 *
 */
public class Transactions {

	private Long id;
	private String status;
	private Long bankOperatorId;
	
    @JsonInclude(Include.NON_NULL)
	private Long chequeBookId;
	
    @JsonInclude(Include.NON_NULL)
	private Long smartDeskId;
    
    @JsonInclude(Include.NON_NULL)
	private long bankclientId;
    
    @JsonInclude(Include.NON_NULL)
	private Long creditCardId;
	
	public Transactions(){
		
	}

	//chequbook  card Ingetion
	public Transactions(Long id, String status, Long bankOperatorId, Long chequeBookId) {
		super();
		this.id = id;
		this.status = status;
		this.bankOperatorId = bankOperatorId;
		this.chequeBookId = chequeBookId;
	}
	
	//credid card Ingetion
	public Transactions(Long id,  Long bankOperatorId, Long creditCardId, String status) {
		super();
		this.id = id;
		this.bankOperatorId = bankOperatorId;
		this.creditCardId = creditCardId;
		this.status = status;
	}

	//chequbook  card  loading
	public Transactions(Long id, String status, Long bankOperatorId, Long chequeBookId, Long smartDeskId) {
		super();
		this.id = id;
		this.status = status;
		this.bankOperatorId = bankOperatorId;
		this.chequeBookId = chequeBookId;
		this.smartDeskId = smartDeskId;
	}
	
	 //credid card  loading
	public Transactions(Long id, Long bankOperatorId, Long creditCardId, Long smartDeskId, String status) {
		super();
		this.id = id;
		this.status = status;
		this.bankOperatorId = bankOperatorId;
		this.creditCardId = creditCardId;
		this.smartDeskId = smartDeskId;
	}
	
	//chequbook card  Delivery
	public Transactions(Long id, String status, Long bankOperatorId, Long chequeBookId, Long smartDeskId,
			long bankclientId) {
		super();
		this.id = id;
		this.status = status;
		this.bankOperatorId = bankOperatorId;
		this.chequeBookId = chequeBookId;
		this.smartDeskId = smartDeskId;
		this.bankclientId = bankclientId;
	}
	//credid card  Delivery
	public Transactions(Long id, Long bankOperatorId, Long creditCardId, Long smartDeskId,
			long bankclientId, String status) {
		super();
		this.id = id;
		this.status = status;
		this.bankOperatorId = bankOperatorId;
		this.creditCardId = creditCardId;
		this.smartDeskId = smartDeskId;
		this.bankclientId = bankclientId;
	}

	public Transactions(Long id, String status, Long bankOperatorId, Long chequeBookId, Long smartDeskId,
			long bankclientId, Long creditCardId) {
		super();
		this.id = id;
		this.status = status;
		this.bankOperatorId = bankOperatorId;
		this.chequeBookId = chequeBookId;
		this.smartDeskId = smartDeskId;
		this.bankclientId = bankclientId;
		this.creditCardId = creditCardId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getBankOperatorId() {
		return bankOperatorId;
	}

	public void setBankOperatorId(Long bankOperatorId) {
		this.bankOperatorId = bankOperatorId;
	}

	public Long getChequeBookId() {
		return chequeBookId;
	}

	public void setChequeBookId(Long chequeBookId) {
		this.chequeBookId = chequeBookId;
	}

	public Long getSmartDeskId() {
		return smartDeskId;
	}

	public void setSmartDeskId(Long smartDeskId) {
		this.smartDeskId = smartDeskId;
	}

	public long getBankclientId() {
		return bankclientId;
	}

	public void setBankclientId(long bankclientId) {
		this.bankclientId = bankclientId;
	}

	public Long getCreditCardId() {
		return creditCardId;
	}

	public void setCreditCardId(Long creditCardId) {
		this.creditCardId = creditCardId;
	}

}


