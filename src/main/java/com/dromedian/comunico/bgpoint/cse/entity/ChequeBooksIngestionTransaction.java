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
@Table(name = "cheque_books_ingestion_transaction")
@JsonIdentityInfo(
		generator= ObjectIdGenerators.PropertyGenerator.class,
property = "id")
public class ChequeBooksIngestionTransaction extends AuditEntity<String> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7431637700597359462L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	private String status;

	//bi-directional many-to-one association to ChequeBook
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="chequebook_id", insertable=false ,updatable=false)
	private ChequeBook chequeBook;

	//bi-directional many-to-one association to BankOperator
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="operator_id")
	private BankOperator bankOperator;
	
	@Transient
	private Long bankOperatorId;
	
	@Transient
	private Long chequeBookId;
	
	
	
	

	public ChequeBooksIngestionTransaction(Long id, String status, ChequeBook chequeBook, BankOperator bankOperator,
			Long bankOperatorId, Long chequeBookId) {
		super();
		this.id = id;
		this.status = status;
		this.chequeBook = chequeBook;
		this.bankOperator = bankOperator;
		this.bankOperatorId = bankOperatorId;
		this.chequeBookId = chequeBookId;
	}

	public ChequeBooksIngestionTransaction(Long id, String status, ChequeBook chequeBook, BankOperator bankOperator) {
		super();
		this.id = id;
		this.status = status;
		this.chequeBook = chequeBook;
		this.bankOperator = bankOperator;
	}

	public Long getBankOperatorId() {
		if(bankOperator!=null){
			bankOperatorId = bankOperator.getId();
		}
		return bankOperatorId;
	}

	public void setBankOperatorId(Long bankOperatorId) {
		this.bankOperatorId = bankOperatorId;
	}

	public Long getChequeBookId() {
		if(chequeBook!=null){
			chequeBookId = chequeBook.getId();
		}
		return chequeBookId;
	}

	public void setChequeBookId(Long chequeBookId) {
		this.chequeBookId = chequeBookId;
	}

	public ChequeBooksIngestionTransaction() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ChequeBook getChequeBook() {
		return this.chequeBook;
	}

	public void setChequeBook(ChequeBook chequeBook) {
		this.chequeBook = chequeBook;
	}

	public BankOperator getBankOperator() {
		return this.bankOperator;
	}

	public void setBankOperator(BankOperator bankOperator) {
		this.bankOperator = bankOperator;
	
	}
}
