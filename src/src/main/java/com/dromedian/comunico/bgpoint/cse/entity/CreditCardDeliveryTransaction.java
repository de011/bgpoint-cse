package com.dromedian.comunico.bgpoint.cse.entity;

import java.io.Serializable;

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
@Table(name = "credit_card_delivery_transaction")
public class CreditCardDeliveryTransaction extends AuditEntity<String> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1948097853870667309L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String status;


	//bi-directional many-to-one association to BankOperator
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="operator_id")
	private BankOperator bankOperator;

	//bi-directional many-to-one association to CreditCard
	@JsonIgnore
	@ManyToOne 
	private CreditCard creditCard;

	//bi-directional many-to-one association to SmartDesk
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="smart_desk_id")
	private SmartDesk smartDesk;

	//bi-directional many-to-one association to BankClient
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="bank_client_id")
	private BankClient bankClient;

	
	@Transient
	private Long creditCardId;
	
	@Transient
	private long smartDeskId;
	
	@Transient
	private long bankclientId;
		
	public CreditCardDeliveryTransaction() {
	}
	
	
	public CreditCardDeliveryTransaction(Long id, BankOperator bankOperator, CreditCard creditCard, SmartDesk smartDesk,
			BankClient bankClient, Long chequeBookId, long smartDeskId, long creditCardId,String status) {
		super();
		this.id = id;
		this.bankOperator = bankOperator;
		this.creditCard = creditCard;
		this.smartDesk = smartDesk;
		this.bankClient = bankClient;
		this.creditCardId = creditCardId;
		this.smartDeskId = smartDeskId;
		this.bankclientId = bankclientId;
		this.status=status;
	}

	public CreditCardDeliveryTransaction(Long id, BankOperator bankOperator, CreditCard creditCard, SmartDesk smartDesk,
			BankClient bankClient, String status) {
		super();
		this.id = id;
		this.bankOperator = bankOperator;
		this.creditCard = creditCard;
		this.smartDesk = smartDesk;
		this.bankClient = bankClient;
		this.status=status;
	}

	
	
	

	public Long getCreditCardId() {
	
		if(creditCard!=null){
			creditCardId = creditCard.getId();
		}
		return creditCardId;
	}


	public void setCreditCardId(Long creditCardId) {
		this.creditCardId = creditCardId;
	}


	public long getSmartDeskId() {
		if(smartDesk!=null){
			smartDeskId=creditCard.getId();
		}
		return smartDeskId;
	}


	public void setSmartDeskId(long smartDeskId) {
		this.smartDeskId = smartDeskId;
	}


	public long getBankclientId() {
		if(bankClient!=null){
			bankclientId=bankClient.getId();
		}
		return bankclientId;
	}


	public void setBankclientId(long bankclientId) {
		this.bankclientId = bankclientId;
	}


	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public SmartDesk getSmartDesk() {
		return this.smartDesk;
	}

	public void setSmartDesk(SmartDesk smartDesk) {
		this.smartDesk = smartDesk;
	}

	public BankClient getBankClient() {
		return this.bankClient;
	}

	public void setBankClient(BankClient bankClient) {
		this.bankClient = bankClient;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}
}
