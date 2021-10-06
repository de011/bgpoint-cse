package com.dromedian.comunico.bgpoint.cse.entity;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author Waseem
 */
@Entity
@Table(name = "cheque_books_delivery_transaction")
public class ChequeBooksDeliveryTransaction extends AuditEntity<String> implements Serializable {

	private static final long serialVersionUID = -1948097853870667309L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	private String status;
	
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name="chequebook_id", insertable=false ,updatable=false)
	private ChequeBook chequeBook;
	
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "smart_desk_id", referencedColumnName = "id", insertable = true, nullable = true)
	private SmartDesk smartDesk;

	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "bank_client_id", nullable = true)
	private BankClient bankClient;

	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "operator_id", referencedColumnName = "id", nullable = true)
	private BankOperator bankOperator;
	
	

	@Transient
	private Long chequeBookId;
	
	@Transient
	private long smartDeskId;
	
	@Transient
	private long bankclientId;
	
	public ChequeBooksDeliveryTransaction() {
	}
		
	public ChequeBooksDeliveryTransaction(Long id, ChequeBook chequeBook, SmartDesk smartDesk, BankClient bankClient,
			BankOperator bankOperator, Long chequeBookId, long smartDeskId, long bankclientId, String status) {
		super();
		this.id = id;
		this.chequeBook = chequeBook;
		this.smartDesk = smartDesk;
		this.bankClient = bankClient;
		this.bankOperator = bankOperator;
		this.chequeBookId = chequeBookId;
		this.smartDeskId = smartDeskId;
		this.bankclientId = bankclientId;
		this.status=status;
	}

	public ChequeBooksDeliveryTransaction(Long id, ChequeBook chequeBook, SmartDesk smartDesk, BankClient bankClient,
			BankOperator bankOperator, String status) {
		super();
		this.id = id;
		this.chequeBook = chequeBook;
		this.smartDesk = smartDesk;
		this.bankClient = bankClient;
		this.bankOperator = bankOperator;
		this.status=status;
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

	public long getSmartDeskId() {
		if(smartDesk!=null){
			smartDeskId=smartDesk.getId();
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
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ChequeBook getChequeBook() {
		return chequeBook;
	}

	public void setChequeBook(ChequeBook chequeBook) {
		this.chequeBook = chequeBook;
	}

	public SmartDesk getSmartDesk() {
		return smartDesk;
	}

	public void setSmartDesk(SmartDesk smartDesk) {
		this.smartDesk = smartDesk;
	}

	public BankClient getBankClient() {
		return bankClient;
	}

	public void setBankClient(BankClient bankClient) {
		this.bankClient = bankClient;
	}

	public BankOperator getBankOperator() {
		return bankOperator;
	}

	public void setBankOperator(BankOperator bankOperator) {
		this.bankOperator = bankOperator;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
