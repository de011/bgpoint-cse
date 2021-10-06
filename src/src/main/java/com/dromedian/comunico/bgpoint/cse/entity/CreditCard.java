package com.dromedian.comunico.bgpoint.cse.entity;
import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author Waseem
 */
@Entity
@Table(name = "credit_card")
public class CreditCard extends AuditEntity<String> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1358691888879242345L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "id")
	private Long id;

	@Column(name="codice_pan")
	private Long codicePan;

	@Column(name="prod")
	private String prod;

	@Column(name="stato")
	private String stato;
	
	//bi-directional many-to-one association to CreditCardDeliveryTransaction
	@JsonIgnore
	@OneToMany(mappedBy="creditCard")
	private List<CreditCardDeliveryTransaction> creditCardDeliveryTransactions;

	//bi-directional many-to-one association to CreditCardIngestionTransaction
	@JsonIgnore
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="creditcard_id", nullable=false)
	private List<CreditCardIngestionTransaction> creditCardIngestionTransactions;

	//bi-directional many-to-one association to CreditCardLoadingTransaction
	@JsonIgnore
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="creditcard_id", nullable=false)
	private List<CreditCardLoadingTransaction> creditCardLoadingTransactions;
	
	@Column(name = "is_deleted", columnDefinition = "int default 0")
	private boolean isDeleted;
	
	@Transient
	private Long noOfSmartDesk= 0L;

	@Transient
	private Long noOfIngestionTransaction = 0L;

	@Transient
	private Long noOfLodingTransaction= 0L;
	
	@Transient
	private Long noOfDeliveryTransaction= 0L;

	public CreditCard() {
	}

	public CreditCard(Long id, Long codicePan,  String prod, String stato,boolean  isDeleted ) {
		super();
		this.id = id;
		this.codicePan = codicePan;
		this.prod = prod;
		this.stato = stato;
		this.isDeleted=isDeleted;
	}
	
	public CreditCard(Long id, Long codicePan, String prod, String stato,
			List<CreditCardDeliveryTransaction> creditCardDeliveryTransactions,
			List<CreditCardIngestionTransaction> creditCardIngestionTransactions,
			List<CreditCardLoadingTransaction> creditCardLoadingTransactions, boolean isDeleted, Long noOfSmartDesk,
			Long noOfIngestionTransaction, Long noOfLodingTransaction, Long noOfDeliveryTransaction) {
		super();
		this.id = id;
		this.codicePan = codicePan;
		this.prod = prod;
		this.stato = stato;
		this.creditCardDeliveryTransactions = creditCardDeliveryTransactions;
		this.creditCardIngestionTransactions = creditCardIngestionTransactions;
		this.creditCardLoadingTransactions = creditCardLoadingTransactions;
		this.isDeleted = isDeleted;
		this.noOfSmartDesk = noOfSmartDesk;
		this.noOfIngestionTransaction = noOfIngestionTransaction;
		this.noOfLodingTransaction = noOfLodingTransaction;
		this.noOfDeliveryTransaction = noOfDeliveryTransaction;
	}

	public CreditCard(Long id, Long codicePan, String prod, String stato, boolean isDeleted, Long noOfSmartDesk,
			Long noOfIngestionTransaction, Long noOfLodingTransaction, Long noOfDeliveryTransaction) {
		super();
		this.id = id;
		this.codicePan = codicePan;
		this.prod = prod;
		this.stato = stato;
		this.isDeleted = isDeleted;
		this.noOfSmartDesk = noOfSmartDesk;
		this.noOfIngestionTransaction = noOfIngestionTransaction;
		this.noOfLodingTransaction = noOfLodingTransaction;
		this.noOfDeliveryTransaction = noOfDeliveryTransaction;
	}
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Long getCodicePan() {
		return codicePan;
	}


	public void setCodicePan(Long codicePan) {
		this.codicePan = codicePan;
	}

	public String getProd() {
		return prod;
	}


	public void setProd(String prod) {
		this.prod = prod;
	}


	public String getStato() {
		return stato;
	}


	public void setStato(String stato) {
		this.stato = stato;
	}


	public List<CreditCardDeliveryTransaction> getCreditCardDeliveryTransactions() {
		return creditCardDeliveryTransactions;
	}


	public void setCreditCardDeliveryTransactions(List<CreditCardDeliveryTransaction> creditCardDeliveryTransactions) {
		this.creditCardDeliveryTransactions = creditCardDeliveryTransactions;
	}


	public List<CreditCardIngestionTransaction> getCreditCardIngestionTransactions() {
		return creditCardIngestionTransactions;
	}


	public void setCreditCardIngestionTransactions(List<CreditCardIngestionTransaction> creditCardIngestionTransactions) {
		this.creditCardIngestionTransactions = creditCardIngestionTransactions;
	}


	public List<CreditCardLoadingTransaction> getCreditCardLoadingTransactions() {
		return creditCardLoadingTransactions;
	}
	
	public void setCreditCardLoadingTransactions(List<CreditCardLoadingTransaction> creditCardLoadingTransactions) {
		this.creditCardLoadingTransactions = creditCardLoadingTransactions;
	}

	public boolean getIsDeleted() {
		return this.isDeleted;
	}

	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Long getNoOfSmartDesk() {
		return noOfSmartDesk;
	}

	public void setNoOfSmartDesk(Long noOfSmartDesk) {
		this.noOfSmartDesk = noOfSmartDesk;
	}

	public Long getNoOfIngestionTransaction() {
		return noOfIngestionTransaction;
	}

	public void setNoOfIngestionTransaction(Long noOfIngestionTransaction) {
		this.noOfIngestionTransaction = noOfIngestionTransaction;
	}

	public Long getNoOfLodingTransaction() {
		return noOfLodingTransaction;
	}

	public void setNoOfLodingTransaction(Long noOfLodingTransaction) {
		this.noOfLodingTransaction = noOfLodingTransaction;
	}

	public Long getNoOfDeliveryTransaction() {
		return noOfDeliveryTransaction;
	}

	public void setNoOfDeliveryTransaction(Long noOfDeliveryTransaction) {
		this.noOfDeliveryTransaction = noOfDeliveryTransaction;
	}		
}
