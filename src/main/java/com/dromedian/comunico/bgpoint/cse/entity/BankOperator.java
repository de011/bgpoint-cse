package com.dromedian.comunico.bgpoint.cse.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author DEEPAK
 *
 */
@Entity
@Table(name = "bank_operator")

public class BankOperator extends AuditEntity<String> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3654234572406314216L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name="bank_operator_name")
	private String bankOperatorName;
	
	@Column(name = "is_deleted", nullable = false, columnDefinition = "int default 0")
	private boolean isDeleted;

	
	@Column(name="operator_id")
	private String operatorId;

	//bi-directional many-to-one association to ChequeBooksDeliveryTransaction
	@JsonIgnore
	@OneToMany(mappedBy="bankOperator")
	private List<ChequeBooksDeliveryTransaction> chequeBooksDeliveryTransactions;

	//bi-directional many-to-one association to ChequeBooksIngestionTransaction
	@JsonIgnore
	@OneToMany(mappedBy="bankOperator")
	private List<ChequeBooksIngestionTransaction> chequeBooksIngestionTransactions;

	//bi-directional many-to-one association to ChequeBooksLoadingTransaction
	@JsonIgnore
	@OneToMany(mappedBy="bankOperator")
	private List<ChequeBooksLoadingTransaction> chequeBooksLoadingTransactions;

	//bi-directional many-to-one association to CreditCardDeliveryTransaction
	@JsonIgnore
	@OneToMany(mappedBy="bankOperator")
	private List<CreditCardDeliveryTransaction> creditCardDeliveryTransactions;

	//bi-directional many-to-one association to CreditCardIngestionTransaction
	@JsonIgnore
	@OneToMany(mappedBy="bankOperator")
	private List<CreditCardIngestionTransaction> creditCardIngestionTransactions;

	//bi-directional many-to-one association to CreditCardLoadingTransaction
	@JsonIgnore
	@OneToMany(mappedBy="bankOperator")
	private List<CreditCardLoadingTransaction> creditCardLoadingTransactions;

	public BankOperator() {
	}
	
	public BankOperator(Long id, String bankOperatorName, boolean isDeleted, String operatorId,
			List<ChequeBooksDeliveryTransaction> chequeBooksDeliveryTransactions,
			List<ChequeBooksIngestionTransaction> chequeBooksIngestionTransactions,
			List<ChequeBooksLoadingTransaction> chequeBooksLoadingTransactions,
			List<CreditCardDeliveryTransaction> creditCardDeliveryTransactions,
			List<CreditCardIngestionTransaction> creditCardIngestionTransactions,
			List<CreditCardLoadingTransaction> creditCardLoadingTransactions) {
		super();
		this.id = id;
		this.bankOperatorName = bankOperatorName;
		this.isDeleted = isDeleted;
		this.operatorId = operatorId;
		this.chequeBooksDeliveryTransactions = chequeBooksDeliveryTransactions;
		this.chequeBooksIngestionTransactions = chequeBooksIngestionTransactions;
		this.chequeBooksLoadingTransactions = chequeBooksLoadingTransactions;
		this.creditCardDeliveryTransactions = creditCardDeliveryTransactions;
		this.creditCardIngestionTransactions = creditCardIngestionTransactions;
		this.creditCardLoadingTransactions = creditCardLoadingTransactions;
	}

	
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean getIsDeleted() {
		return this.isDeleted;
	}

	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getOperatorId() {
		return this.operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public String getBankOperatorName() {
		return bankOperatorName;
	}
	
	public void setBankOperatorName(String bankOperatorName) {
		this.bankOperatorName = bankOperatorName;
	}

	public List<ChequeBooksDeliveryTransaction> getChequeBooksDeliveryTransactions() {
		return this.chequeBooksDeliveryTransactions;
	}

	public void setChequeBooksDeliveryTransactions(List<ChequeBooksDeliveryTransaction> chequeBooksDeliveryTransactions) {
		this.chequeBooksDeliveryTransactions = chequeBooksDeliveryTransactions;
	}

	public ChequeBooksDeliveryTransaction addChequeBooksDeliveryTransaction(ChequeBooksDeliveryTransaction chequeBooksDeliveryTransaction) {
		getChequeBooksDeliveryTransactions().add(chequeBooksDeliveryTransaction);
		chequeBooksDeliveryTransaction.setBankOperator(this);

		return chequeBooksDeliveryTransaction;
	}

	public ChequeBooksDeliveryTransaction removeChequeBooksDeliveryTransaction(ChequeBooksDeliveryTransaction chequeBooksDeliveryTransaction) {
		getChequeBooksDeliveryTransactions().remove(chequeBooksDeliveryTransaction);
		chequeBooksDeliveryTransaction.setBankOperator(null);

		return chequeBooksDeliveryTransaction;
	}

	public List<ChequeBooksIngestionTransaction> getChequeBooksIngestionTransactions() {
		return this.chequeBooksIngestionTransactions;
	}

	public void setChequeBooksIngestionTransactions(List<ChequeBooksIngestionTransaction> chequeBooksIngestionTransactions) {
		this.chequeBooksIngestionTransactions = chequeBooksIngestionTransactions;
	}

	public ChequeBooksIngestionTransaction addChequeBooksIngestionTransaction(ChequeBooksIngestionTransaction chequeBooksIngestionTransaction) {
		getChequeBooksIngestionTransactions().add(chequeBooksIngestionTransaction);
		chequeBooksIngestionTransaction.setBankOperator(this);

		return chequeBooksIngestionTransaction;
	}

	public ChequeBooksIngestionTransaction removeChequeBooksIngestionTransaction(ChequeBooksIngestionTransaction chequeBooksIngestionTransaction) {
		getChequeBooksIngestionTransactions().remove(chequeBooksIngestionTransaction);
		chequeBooksIngestionTransaction.setBankOperator(null);

		return chequeBooksIngestionTransaction;
	}

	public List<ChequeBooksLoadingTransaction> getChequeBooksLoadingTransactions() {
		return this.chequeBooksLoadingTransactions;
	}

	public void setChequeBooksLoadingTransactions(List<ChequeBooksLoadingTransaction> chequeBooksLoadingTransactions) {
		this.chequeBooksLoadingTransactions = chequeBooksLoadingTransactions;
	}

	public ChequeBooksLoadingTransaction addChequeBooksLoadingTransaction(ChequeBooksLoadingTransaction chequeBooksLoadingTransaction) {
		getChequeBooksLoadingTransactions().add(chequeBooksLoadingTransaction);
		chequeBooksLoadingTransaction.setBankOperator(this);

		return chequeBooksLoadingTransaction;
	}

	public ChequeBooksLoadingTransaction removeChequeBooksLoadingTransaction(ChequeBooksLoadingTransaction chequeBooksLoadingTransaction) {
		getChequeBooksLoadingTransactions().remove(chequeBooksLoadingTransaction);
		chequeBooksLoadingTransaction.setBankOperator(null);

		return chequeBooksLoadingTransaction;
	}

	public List<CreditCardDeliveryTransaction> getCreditCardDeliveryTransactions() {
		return this.creditCardDeliveryTransactions;
	}

	public void setCreditCardDeliveryTransactions(List<CreditCardDeliveryTransaction> creditCardDeliveryTransactions) {
		this.creditCardDeliveryTransactions = creditCardDeliveryTransactions;
	}

	public CreditCardDeliveryTransaction addCreditCardDeliveryTransaction(CreditCardDeliveryTransaction creditCardDeliveryTransaction) {
		getCreditCardDeliveryTransactions().add(creditCardDeliveryTransaction);
		creditCardDeliveryTransaction.setBankOperator(this);

		return creditCardDeliveryTransaction;
	}

	public CreditCardDeliveryTransaction removeCreditCardDeliveryTransaction(CreditCardDeliveryTransaction creditCardDeliveryTransaction) {
		getCreditCardDeliveryTransactions().remove(creditCardDeliveryTransaction);
		creditCardDeliveryTransaction.setBankOperator(null);

		return creditCardDeliveryTransaction;
	}

	public List<CreditCardIngestionTransaction> getCreditCardIngestionTransactions() {
		return this.creditCardIngestionTransactions;
	}

	public void setCreditCardIngestionTransactions(List<CreditCardIngestionTransaction> creditCardIngestionTransactions) {
		this.creditCardIngestionTransactions = creditCardIngestionTransactions;
	}

	public CreditCardIngestionTransaction addCreditCardIngestionTransaction(CreditCardIngestionTransaction creditCardIngestionTransaction) {
		getCreditCardIngestionTransactions().add(creditCardIngestionTransaction);
		creditCardIngestionTransaction.setBankOperator(this);

		return creditCardIngestionTransaction;
	}

	public CreditCardIngestionTransaction removeCreditCardIngestionTransaction(CreditCardIngestionTransaction creditCardIngestionTransaction) {
		getCreditCardIngestionTransactions().remove(creditCardIngestionTransaction);
		creditCardIngestionTransaction.setBankOperator(null);

		return creditCardIngestionTransaction;
	}

	public List<CreditCardLoadingTransaction> getCreditCardLoadingTransactions() {
		return this.creditCardLoadingTransactions;
	}

	public void setCreditCardLoadingTransactions(List<CreditCardLoadingTransaction> creditCardLoadingTransactions) {
		this.creditCardLoadingTransactions = creditCardLoadingTransactions;
	}

	public CreditCardLoadingTransaction addCreditCardLoadingTransaction(CreditCardLoadingTransaction creditCardLoadingTransaction) {
		getCreditCardLoadingTransactions().add(creditCardLoadingTransaction);
		creditCardLoadingTransaction.setBankOperator(this);

		return creditCardLoadingTransaction;
	}

	public CreditCardLoadingTransaction removeCreditCardLoadingTransaction(CreditCardLoadingTransaction creditCardLoadingTransaction) {
		getCreditCardLoadingTransactions().remove(creditCardLoadingTransaction);
		creditCardLoadingTransaction.setBankOperator(null);

		return creditCardLoadingTransaction;
	}

}
