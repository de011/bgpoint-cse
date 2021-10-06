package com.dromedian.comunico.bgpoint.cse.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author Waseem
 */
@Entity
@Table(name = "credit_card_ingestion_transaction")
public class CreditCardIngestionTransaction extends AuditEntity<String> implements Serializable {
	/**
	 * 
	 */

	private static final long serialVersionUID = 7431637700597359462L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "id")
	private long id;


	private String status;

	//bi-directional many-to-one association to BankOperator
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="operator_id")
	private BankOperator bankOperator;

	//bi-directional many-to-one association to CreditCard
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="creditcard_id", insertable=false ,updatable=false)
	private CreditCard creditCard;
	
	@Transient
	private Long bankOperatorId;
	
	@Transient
	private Long creditCardId;
	
	
	public CreditCardIngestionTransaction() {
	}

	
	public CreditCardIngestionTransaction(long id, String status, BankOperator bankOperator, CreditCard creditCard,
			Long bankOperatorId, Long creditCardId) {
		super();
		this.id = id;
		this.status = status;
		this.bankOperator = bankOperator;
		this.creditCard = creditCard;
		this.bankOperatorId = bankOperatorId;
		this.creditCardId = creditCardId;
	}


	public CreditCardIngestionTransaction(long id, String status, BankOperator bankOperator, CreditCard creditCard) {
		super();
		this.id = id;
		this.status = status;
		this.bankOperator = bankOperator;
		this.creditCard = creditCard;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public BankOperator getBankOperator() {
		return bankOperator;
	}


	public void setBankOperator(BankOperator bankOperator) {
		this.bankOperator = bankOperator;
	}


	public CreditCard getCreditCard() {
		return creditCard;
	}


	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}


	public Long getBankOperatorId() {
		if(bankOperator!=null)
		{
			bankOperatorId=bankOperator.getId();
		}
		return bankOperatorId;
	}


	public void setBankOperatorId(Long bankOperatorId) {
		this.bankOperatorId = bankOperatorId;
	}


	public Long getCreditCardId() {
		if(creditCard!=null)
		{
			creditCardId=creditCard.getId();
		}
		return creditCardId;
	}

	public void setCreditCardId(Long creditCardId) {
		this.creditCardId = creditCardId;
	}
}
