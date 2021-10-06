package com.dromedian.comunico.bgpoint.cse.entity;
import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author Waseem
 */
@Entity
@Table(name = "smart_desk")
public class SmartDesk extends AuditEntity<String> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4598648834423631648L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name="external_id")
	private String externalId;

	@Column(name="smart_desk_id")
	private String smartDeskId;

	@Column(name="smart_desk_name")
	private String smartDeskName;

	@Column(name = "is_deleted", columnDefinition = "int default 0")
	private boolean isDeleted; 

	//bi-directional many-to-one association to ChequeBooksDeliveryTransaction
	@JsonIgnore
	@OneToMany(mappedBy="smartDesk")
	private List<ChequeBooksDeliveryTransaction> chequeBooksDeliveryTransactions;

	//bi-directional many-to-one association to ChequeBooksLoadingTransaction
	@JsonIgnore
	@OneToMany(mappedBy="smartDesk")
	private List<ChequeBooksLoadingTransaction> chequeBooksLoadingTransactions;

	//bi-directional many-to-one association to CreditCardDeliveryTransaction
	@JsonIgnore
	@OneToMany(mappedBy="smartDesk")
	private List<CreditCardDeliveryTransaction> creditCardDeliveryTransactions;

	//bi-directional many-to-one association to CreditCardLoadingTransaction
	@JsonIgnore
	@OneToMany(mappedBy="smartDesk")
	private List<CreditCardLoadingTransaction> creditCardLoadingTransactions;

	
	//bi-directional many-to-one association to BankBranch
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="bank_branch_id")
	private BankBranch bankBranch;


	@Transient
	private Long bankBranchId;

	@Transient
	private Long noOfChequeBooks;

	@Transient
	private Long noOfCards;


	public SmartDesk() {
	}

	public SmartDesk(Long id, String externalId, String smartDeskId, String smartDeskName, boolean isDeleted,
			List<ChequeBooksDeliveryTransaction> chequeBooksDeliveryTransactions,
			List<ChequeBooksLoadingTransaction> chequeBooksLoadingTransactions,
			List<CreditCardDeliveryTransaction> creditCardDeliveryTransactions,
			List<CreditCardLoadingTransaction> creditCardLoadingTransactions, BankBranch bankBranch,
			Long bankBranchId) {
		super();
		this.id = id;
		this.externalId = externalId;
		this.smartDeskId = smartDeskId;
		this.smartDeskName = smartDeskName;
		this.isDeleted = isDeleted;
		this.chequeBooksDeliveryTransactions = chequeBooksDeliveryTransactions;
		this.chequeBooksLoadingTransactions = chequeBooksLoadingTransactions;
		this.creditCardDeliveryTransactions = creditCardDeliveryTransactions;
		this.creditCardLoadingTransactions = creditCardLoadingTransactions;
		this.bankBranch = bankBranch;
		this.bankBranchId = bankBranchId;
	}

	public SmartDesk(Long id, String smartDeskId, BankBranch bankBranch, String smartDeskName, String externalId,boolean isDeleted,  
			Long noOfChequeBooks, Long noOfCards) {
		super();
		this.id = id;
		this.externalId = externalId;
		this.smartDeskId = smartDeskId;
		this.smartDeskName = smartDeskName;
		this.isDeleted = isDeleted;
		this.bankBranch = bankBranch;
		this.noOfChequeBooks = noOfChequeBooks;
		this.noOfCards = noOfCards;
	}
	
	public SmartDesk(Long id, String externalId, String smartDeskId, String smartDeskName, boolean isDeleted, BankBranch bankBranch, Long bankBranchId,
			Long noOfChequeBooks, Long noOfCards) {
		super();
		this.id = id;
		this.externalId = externalId;
		this.smartDeskId = smartDeskId;
		this.smartDeskName = smartDeskName;
		this.isDeleted = isDeleted;
		this.bankBranch = bankBranch;
		this.bankBranchId = bankBranchId;
		this.noOfChequeBooks = noOfChequeBooks;
		this.noOfCards = noOfCards;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getExternalId() {
		return this.externalId;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}

	public boolean getIsDeleted() {
		return this.isDeleted;
	}

	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getSmartDeskId() {
		return this.smartDeskId;
	}

	public void setSmartDeskId(String smartDeskId) {
		this.smartDeskId = smartDeskId;
	}

	public String getSmartDeskName() {
		return this.smartDeskName;
	}

	public void setSmartDeskName(String smartDeskName) {
		this.smartDeskName = smartDeskName;
	}

	public List<ChequeBooksDeliveryTransaction> getChequeBooksDeliveryTransactions() {
		return this.chequeBooksDeliveryTransactions;
	}

	public void setChequeBooksDeliveryTransactions(List<ChequeBooksDeliveryTransaction> chequeBooksDeliveryTransactions) {
		this.chequeBooksDeliveryTransactions = chequeBooksDeliveryTransactions;
	}

	public ChequeBooksDeliveryTransaction addChequeBooksDeliveryTransaction(ChequeBooksDeliveryTransaction chequeBooksDeliveryTransaction) {
		getChequeBooksDeliveryTransactions().add(chequeBooksDeliveryTransaction);
		chequeBooksDeliveryTransaction.setSmartDesk(this);

		return chequeBooksDeliveryTransaction;
	}

	public ChequeBooksDeliveryTransaction removeChequeBooksDeliveryTransaction(ChequeBooksDeliveryTransaction chequeBooksDeliveryTransaction) {
		getChequeBooksDeliveryTransactions().remove(chequeBooksDeliveryTransaction);
		chequeBooksDeliveryTransaction.setSmartDesk(null);

		return chequeBooksDeliveryTransaction;
	}

	public List<ChequeBooksLoadingTransaction> getChequeBooksLoadingTransactions() {
		return this.chequeBooksLoadingTransactions;
	}

	public void setChequeBooksLoadingTransactions(List<ChequeBooksLoadingTransaction> chequeBooksLoadingTransactions) {
		this.chequeBooksLoadingTransactions = chequeBooksLoadingTransactions;
	}

	public ChequeBooksLoadingTransaction addChequeBooksLoadingTransaction(ChequeBooksLoadingTransaction chequeBooksLoadingTransaction) {
		getChequeBooksLoadingTransactions().add(chequeBooksLoadingTransaction);
		chequeBooksLoadingTransaction.setSmartDesk(this);

		return chequeBooksLoadingTransaction;
	}

	public ChequeBooksLoadingTransaction removeChequeBooksLoadingTransaction(ChequeBooksLoadingTransaction chequeBooksLoadingTransaction) {
		getChequeBooksLoadingTransactions().remove(chequeBooksLoadingTransaction);
		chequeBooksLoadingTransaction.setSmartDesk(null);

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
		creditCardDeliveryTransaction.setSmartDesk(this);

		return creditCardDeliveryTransaction;
	}

	public CreditCardDeliveryTransaction removeCreditCardDeliveryTransaction(CreditCardDeliveryTransaction creditCardDeliveryTransaction) {
		getCreditCardDeliveryTransactions().remove(creditCardDeliveryTransaction);
		creditCardDeliveryTransaction.setSmartDesk(null);

		return creditCardDeliveryTransaction;
	}

	public List<CreditCardLoadingTransaction> getCreditCardLoadingTransactions() {
		return this.creditCardLoadingTransactions;
	}

	public void setCreditCardLoadingTransactions(List<CreditCardLoadingTransaction> creditCardLoadingTransactions) {
		this.creditCardLoadingTransactions = creditCardLoadingTransactions;
	}

	public CreditCardLoadingTransaction addCreditCardLoadingTransaction(CreditCardLoadingTransaction creditCardLoadingTransaction) {
		getCreditCardLoadingTransactions().add(creditCardLoadingTransaction);
		creditCardLoadingTransaction.setSmartDesk(this);

		return creditCardLoadingTransaction;
	}

	public CreditCardLoadingTransaction removeCreditCardLoadingTransaction(CreditCardLoadingTransaction creditCardLoadingTransaction) {
		getCreditCardLoadingTransactions().remove(creditCardLoadingTransaction);
		creditCardLoadingTransaction.setSmartDesk(null);

		return creditCardLoadingTransaction;
	}

	public BankBranch getBankBranch() {
		return this.bankBranch;
	}

	public void setBankBranch(BankBranch bankBranch) {
		this.bankBranch = bankBranch;
	}

	public Long getBankBranchId() {

		if(bankBranch!=null) {
			return bankBranch.getId();
		}else {
			return bankBranchId;
		}
	}

	public void setBankBranchId(Long bankBranchId) {
		this.bankBranchId = bankBranchId;
	}
	public Long getNoOfChequeBooks() {
		return noOfChequeBooks;
	}

	public void setNoOfChequeBooks(Long noOfChequeBooks) {
		this.noOfChequeBooks = noOfChequeBooks;
	}

	public Long getNoOfCards() {
		return noOfCards;
	}

	public void setNoOfCards(Long noOfCards) {
		this.noOfCards = noOfCards;
	}

	@PreUpdate
	@PrePersist
	public void onCreate(){
		if(bankBranchId!=null){
			BankBranch branch = new BankBranch();
			branch.setId(bankBranchId);
			this.bankBranch= branch;
		}
	}	
}
