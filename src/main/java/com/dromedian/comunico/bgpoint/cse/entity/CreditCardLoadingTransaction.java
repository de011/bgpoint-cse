package com.dromedian.comunico.bgpoint.cse.entity;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * @author DEEPAK
 *
 */
@Entity
@Table(name = "credit_card_loading_transaction")
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class,
property = "id")
public class CreditCardLoadingTransaction extends AuditEntity<String> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 903708269261029703L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "id")
	private Long id;
	
	private String status;
	
	//bi-directional many-to-one association to SmartDesk
	@ManyToOne
	@JoinColumn(name="smart_desk_id")
	private SmartDesk smartDesk;

	//bi-directional many-to-one association to BankOperator
	@ManyToOne
	@JoinColumn(name="operator_id")
	private BankOperator bankOperator;

	//bi-directional many-to-one association to CreditCard
	@ManyToOne
	@JoinColumn(name="creditcard_id", insertable=false ,updatable=false)
	private CreditCard creditCard;

	
	@Transient
	private Long bankOperatorId;
	
	@Transient
	private Long creditCardId;
	
	
	@Transient
	private Long smartDeskId;
	
	public CreditCardLoadingTransaction() {

	}
	
	public CreditCardLoadingTransaction(Long id, SmartDesk smartDesk, BankOperator bankOperator, CreditCard creditCard,
			Long bankOperatorId, Long creditCardId, Long smartDeskId,String status) {
		super();
		this.id = id;
		this.smartDesk = smartDesk;
		this.bankOperator = bankOperator;
		this.creditCard = creditCard;
		this.bankOperatorId = bankOperatorId;
		this.creditCardId = creditCardId;
		this.smartDeskId = smartDeskId;
		this.status=status;
	}
	
	public CreditCardLoadingTransaction(Long id, SmartDesk smartDesk, BankOperator bankOperator,
			CreditCard creditCard, String status) {
		super();
		this.id = id;
		this.smartDesk = smartDesk;
		this.bankOperator = bankOperator;
		this.creditCard = creditCard;
	}

	public Long getBankOperatorId() {
		if(bankOperator!=null){
			bankOperatorId=bankOperator.getId();
		}
		return bankOperatorId;
	}

	public void setBankOperatorId(Long bankOperatorId) {
		this.bankOperatorId = bankOperatorId;
	}

	public Long getCreditCardId() {
		if(creditCard!=null){
			creditCardId=creditCard.getId();
		}
		return creditCardId;
	}

	public void setCreditCardId(Long creditCardId) {
		this.creditCardId = creditCardId;
	}

	public Long getSmartDeskId() {
		if(smartDesk!=null){
			smartDeskId=smartDesk.getId();
		}
		return smartDeskId;
	}

	public void setSmartDeskId(Long smartDeskId) {
		this.smartDeskId = smartDeskId;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public SmartDesk getSmartDesk() {
		return this.smartDesk;
	}

	public void setSmartDesk(SmartDesk smartDesk) {
		this.smartDesk = smartDesk;
	}

	public BankOperator getBankOperator() {
		return this.bankOperator;
	}

	public void setBankOperator(BankOperator bankOperator) {
		this.bankOperator = bankOperator;
	}

	public CreditCard getCreditCard() {
		return this.creditCard;
	}

	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;		
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}