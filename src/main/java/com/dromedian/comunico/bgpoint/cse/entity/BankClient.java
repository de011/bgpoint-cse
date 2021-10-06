package com.dromedian.comunico.bgpoint.cse.entity;
import java.io.Serializable;
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

/**
 * @author DEEPAK
 *
 */
@Entity
@Table(name = "bank_client")

public class BankClient extends AuditEntity<String> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4886233664124807991L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Long id;
	private String cdg;

	@Column(name="cell_cert")
	private String cellCert;

	private String circuito;

	private String concat;

	private String concnt;

	private String confil;

	@Column(name="data_nascita")
	private String dataNascita;

	private String des;

	@Column(name="gg_mancanti")
	private String ggMancanti;

	private String inddis;


	private String statoppb;

	@Column(name="user_id")
	private Integer userId;

	@Column(name="user_name")
	private String userName;

	//bi-directional many-to-one association to ChequeBooksDeliveryTransaction
	@OneToMany(mappedBy="bankClient")
	private List<ChequeBooksDeliveryTransaction> chequeBooksDeliveryTransactions;

	//bi-directional many-to-one association to CreditCardDeliveryTransaction
	@OneToMany(mappedBy="bankClient")
	private List<CreditCardDeliveryTransaction> creditCardDeliveryTransactions;

	@Column(name = "is_deleted", nullable = false, columnDefinition = "int default 0")
	private boolean isDeleted;

	
	
	public BankClient() {
	
	}



	public BankClient(Long id, String cdg, String cellCert, String circuito, String concat, String concnt,
			String confil, String dataNascita, String des, String ggMancanti, String inddis, String statoppb,
			Integer userId, String userName, List<ChequeBooksDeliveryTransaction> chequeBooksDeliveryTransactions,
			List<CreditCardDeliveryTransaction> creditCardDeliveryTransactions, boolean isDeleted) {
		super();
		this.id = id;
		this.cdg = cdg;
		this.cellCert = cellCert;
		this.circuito = circuito;
		this.concat = concat;
		this.concnt = concnt;
		this.confil = confil;
		this.dataNascita = dataNascita;
		this.des = des;
		this.ggMancanti = ggMancanti;
		this.inddis = inddis;
		this.statoppb = statoppb;
		this.userId = userId;
		this.userName = userName;
		this.chequeBooksDeliveryTransactions = chequeBooksDeliveryTransactions;
		this.creditCardDeliveryTransactions = creditCardDeliveryTransactions;
		this.isDeleted = isDeleted;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getCdg() {
		return cdg;
	}



	public void setCdg(String cdg) {
		this.cdg = cdg;
	}



	public String getCellCert() {
		return cellCert;
	}



	public void setCellCert(String cellCert) {
		this.cellCert = cellCert;
	}



	public String getCircuito() {
		return circuito;
	}



	public void setCircuito(String circuito) {
		this.circuito = circuito;
	}



	public String getConcat() {
		return concat;
	}



	public void setConcat(String concat) {
		this.concat = concat;
	}



	public String getConcnt() {
		return concnt;
	}



	public void setConcnt(String concnt) {
		this.concnt = concnt;
	}



	public String getConfil() {
		return confil;
	}



	public void setConfil(String confil) {
		this.confil = confil;
	}



	public String getDataNascita() {
		return dataNascita;
	}



	public void setDataNascita(String dataNascita) {
		this.dataNascita = dataNascita;
	}



	public String getDes() {
		return des;
	}



	public void setDes(String des) {
		this.des = des;
	}



	public String getGgMancanti() {
		return ggMancanti;
	}



	public void setGgMancanti(String ggMancanti) {
		this.ggMancanti = ggMancanti;
	}



	public String getInddis() {
		return inddis;
	}



	public void setInddis(String inddis) {
		this.inddis = inddis;
	}



	public String getStatoppb() {
		return statoppb;
	}



	public void setStatoppb(String statoppb) {
		this.statoppb = statoppb;
	}



	public Integer getUserId() {
		return userId;
	}



	public void setUserId(Integer userId) {
		this.userId = userId;
	}



	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}



	public List<ChequeBooksDeliveryTransaction> getChequeBooksDeliveryTransactions() {
		return chequeBooksDeliveryTransactions;
	}



	public void setChequeBooksDeliveryTransactions(List<ChequeBooksDeliveryTransaction> chequeBooksDeliveryTransactions) {
		this.chequeBooksDeliveryTransactions = chequeBooksDeliveryTransactions;
	}



	public List<CreditCardDeliveryTransaction> getCreditCardDeliveryTransactions() {
		return creditCardDeliveryTransactions;
	}



	public void setCreditCardDeliveryTransactions(List<CreditCardDeliveryTransaction> creditCardDeliveryTransactions) {
		this.creditCardDeliveryTransactions = creditCardDeliveryTransactions;
	}



	public boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}	
}
