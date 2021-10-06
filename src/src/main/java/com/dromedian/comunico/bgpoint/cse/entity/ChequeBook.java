package com.dromedian.comunico.bgpoint.cse.entity;
import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author Waseem
 */

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "cheque_book")
public class ChequeBook extends AuditEntity<String> implements Serializable {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = -8092795044257862523L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "id")
	private Long id;
	
	@Column(name="cab_assegno")
	private Integer cabAssegno;

	@Column(name="cin_primo_assegno")
	private Integer cinPrimoAssegno;

	@Column(name="cod_categoria")
	private Integer codCategoria;

	@Column(name="cod_gruppo")
	private Integer codGruppo;

	@Column(name = "codice_univoco", unique = true)
	private Long codiceUnivoco;

	@Column(name="fil_assegno")
	private Integer filAssegno;

	@Column(name="id_azione")
	private Integer idAzione;

	@Column(name="nr_assegni")
	private Integer nrAssegni;

	@Column(name="nr_primo_assegno")
	private Integer nrPrimoAssegno;

	@Column(name="nr_ultimo_assegno")
	private Integer nrUltimoAssegno;

	@Column(name="num_rapporto")
	private Integer numRapporto;

	@Column(name="request_id")
	private String requestId;

	@Column(name="serie_assegni")
	private String serieAssegni;
	
	//bi-directional many-to-one association to ChequeBooksDeliveryTransaction
	@JsonIgnore
	@OneToMany(mappedBy="chequeBook")
	private List<ChequeBooksDeliveryTransaction> chequeBooksDeliveryTransactions;

	//bi-directional many-to-one association to ChequeBooksIngestionTransaction
	@JsonIgnore
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="chequebook_id", nullable=false)
	private List<ChequeBooksIngestionTransaction> chequeBooksIngestionTransactions;

	//bi-directional many-to-one association to ChequeBooksLoadingTransaction
	@JsonIgnore
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="chequebook_id", nullable=false)
	private List<ChequeBooksLoadingTransaction> chequeBooksLoadingTransactions;

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

	
	public ChequeBook() {
	}
	
	public ChequeBook(Long id, Integer cabAssegno, Integer cinPrimoAssegno, Integer codCategoria, Integer codGruppo,
			Long codiceUnivoco, Integer filAssegno, Integer idAzione, Integer nrAssegni, Integer nrPrimoAssegno,
			Integer nrUltimoAssegno, Integer numRapporto, String requestId, String serieAssegni,boolean isDeleted ) {
		
		super();
		this.id = id;
		this.cabAssegno = cabAssegno;
		this.cinPrimoAssegno = cinPrimoAssegno;
		this.codCategoria = codCategoria;
		this.codGruppo = codGruppo;
		this.codiceUnivoco = codiceUnivoco;
		this.filAssegno = filAssegno;
		this.idAzione = idAzione;
		this.nrAssegni = nrAssegni;
		this.nrPrimoAssegno = nrPrimoAssegno;
		this.nrUltimoAssegno = nrUltimoAssegno;
		this.numRapporto = numRapporto;
		this.requestId = requestId;
		this.serieAssegni = serieAssegni;
		this.isDeleted=isDeleted;
	}
	


	public ChequeBook(Long id, Integer cabAssegno, Integer cinPrimoAssegno, Integer codCategoria, Integer codGruppo,
			Long codiceUnivoco, Integer filAssegno, Integer idAzione, Integer nrAssegni, Integer nrPrimoAssegno,
			Integer nrUltimoAssegno, Integer numRapporto, String requestId, String serieAssegni,
			List<ChequeBooksDeliveryTransaction> chequeBooksDeliveryTransactions,
			List<ChequeBooksIngestionTransaction> chequeBooksIngestionTransactions,
			List<ChequeBooksLoadingTransaction> chequeBooksLoadingTransactions, boolean isDeleted, Long noOfSmartDesk,
			Long noOfIngestionTransaction, Long noOfLodingTransaction, Long noOfDeliveryTransaction) {
		super();
		this.id = id;
		this.cabAssegno = cabAssegno;
		this.cinPrimoAssegno = cinPrimoAssegno;
		this.codCategoria = codCategoria;
		this.codGruppo = codGruppo;
		this.codiceUnivoco = codiceUnivoco;
		this.filAssegno = filAssegno;
		this.idAzione = idAzione;
		this.nrAssegni = nrAssegni;
		this.nrPrimoAssegno = nrPrimoAssegno;
		this.nrUltimoAssegno = nrUltimoAssegno;
		this.numRapporto = numRapporto;
		this.requestId = requestId;
		this.serieAssegni = serieAssegni;
		this.chequeBooksDeliveryTransactions = chequeBooksDeliveryTransactions;
		this.chequeBooksIngestionTransactions = chequeBooksIngestionTransactions;
		this.chequeBooksLoadingTransactions = chequeBooksLoadingTransactions;
		this.isDeleted = isDeleted;
		this.noOfSmartDesk = noOfSmartDesk;
		this.noOfIngestionTransaction = noOfIngestionTransaction;
		this.noOfLodingTransaction = noOfLodingTransaction;
		this.noOfDeliveryTransaction = noOfDeliveryTransaction;
	}
	
	public ChequeBook(Long id, Integer cabAssegno, Integer cinPrimoAssegno, Integer codCategoria, Integer codGruppo,
			Long codiceUnivoco, Integer filAssegno, Integer idAzione, Integer nrAssegni, Integer nrPrimoAssegno,
			Integer nrUltimoAssegno, Integer numRapporto, String requestId, String serieAssegni, boolean isDeleted, Long noOfSmartDesk,
			Long noOfIngestionTransaction, Long noOfLodingTransaction, Long noOfDeliveryTransaction) {
		super();
		this.id = id;
		this.cabAssegno = cabAssegno;
		this.cinPrimoAssegno = cinPrimoAssegno;
		this.codCategoria = codCategoria;
		this.codGruppo = codGruppo;
		this.codiceUnivoco = codiceUnivoco;
		this.filAssegno = filAssegno;
		this.idAzione = idAzione;
		this.nrAssegni = nrAssegni;
		this.nrPrimoAssegno = nrPrimoAssegno;
		this.nrUltimoAssegno = nrUltimoAssegno;
		this.numRapporto = numRapporto;
		this.requestId = requestId;
		this.serieAssegni = serieAssegni;
		this.isDeleted = isDeleted;
		this.noOfSmartDesk = noOfSmartDesk;
		this.noOfIngestionTransaction = noOfIngestionTransaction;
		this.noOfLodingTransaction = noOfLodingTransaction;
		this.noOfDeliveryTransaction = noOfDeliveryTransaction;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCabAssegno() {
		return this.cabAssegno;
	}

	public void setCabAssegno(Integer cabAssegno) {
		this.cabAssegno = cabAssegno;
	}

	public Integer getCinPrimoAssegno() {
		return this.cinPrimoAssegno;
	}

	public void setCinPrimoAssegno(Integer cinPrimoAssegno) {
		this.cinPrimoAssegno = cinPrimoAssegno;
	}

	public Integer getCodCategoria() {
		return this.codCategoria;
	}

	public void setCodCategoria(Integer codCategoria) {
		this.codCategoria = codCategoria;
	}

	public Integer getCodGruppo() {
		return this.codGruppo;
	}

	public void setCodGruppo(Integer codGruppo) {
		this.codGruppo = codGruppo;
	}

	public Long getCodiceUnivoco() {
		return this.codiceUnivoco;
	}

	public void setCodiceUnivoco(Long codiceUnivoco) {
		this.codiceUnivoco = codiceUnivoco;
	}
	
	public Integer getFilAssegno() {
		return this.filAssegno;
	}

	public void setFilAssegno(Integer filAssegno) {
		this.filAssegno = filAssegno;
	}

	public Integer getIdAzione() {
		return this.idAzione;
	}

	public void setIdAzione(Integer idAzione) {
		this.idAzione = idAzione;
	}

	public Integer getNrAssegni() {
		return this.nrAssegni;
	}

	public void setNrAssegni(Integer nrAssegni) {
		this.nrAssegni = nrAssegni;
	}

	public Integer getNrPrimoAssegno() {
		return this.nrPrimoAssegno;
	}

	public void setNrPrimoAssegno(Integer nrPrimoAssegno) {
		this.nrPrimoAssegno = nrPrimoAssegno;
	}

	public Integer getNrUltimoAssegno() {
		return this.nrUltimoAssegno;
	}

	public void setNrUltimoAssegno(Integer nrUltimoAssegno) {
		this.nrUltimoAssegno = nrUltimoAssegno;
	}

	public Integer getNumRapporto() {
		return this.numRapporto;
	}

	public void setNumRapporto(Integer numRapporto) {
		this.numRapporto = numRapporto;
	}

	public String getRequestId() {
		return this.requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getSerieAssegni() {
		return this.serieAssegni;
	}

	public void setSerieAssegni(String serieAssegni) {
		this.serieAssegni = serieAssegni;
	}

	public List<ChequeBooksDeliveryTransaction> getChequeBooksDeliveryTransactions() {
		return this.chequeBooksDeliveryTransactions;
	}

	public void setChequeBooksDeliveryTransactions(List<ChequeBooksDeliveryTransaction> chequeBooksDeliveryTransactions) {
		this.chequeBooksDeliveryTransactions = chequeBooksDeliveryTransactions;
	}



	public List<ChequeBooksIngestionTransaction> getChequeBooksIngestionTransactions() {
		return this.chequeBooksIngestionTransactions;
	}

	public void setChequeBooksIngestionTransactions(List<ChequeBooksIngestionTransaction> chequeBooksIngestionTransactions) {
		this.chequeBooksIngestionTransactions = chequeBooksIngestionTransactions;
	}
	
	public List<ChequeBooksLoadingTransaction> getChequeBooksLoadingTransactions() {
		return this.chequeBooksLoadingTransactions;
	}

	public void setChequeBooksLoadingTransactions(List<ChequeBooksLoadingTransaction> chequeBooksLoadingTransactions) {
		this.chequeBooksLoadingTransactions = chequeBooksLoadingTransactions;
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