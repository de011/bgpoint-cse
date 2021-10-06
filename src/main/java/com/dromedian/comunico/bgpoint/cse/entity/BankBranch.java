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
import javax.persistence.Transient;

import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * @author DEEPAK
 *
 */
@Entity
@Table(name = "bank_branch")
public class BankBranch extends AuditEntity<String> implements Serializable {

	private static final long serialVersionUID = 5863647568296780L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "id")
	private Long id;

	@Column(name="branch_name")
	private String branchName;


	@Column(name="external_id")
	private String externalId;

	@Column(name="is_deleted")
	private boolean isDeleted;
	
	//bi-directional many-to-one association to SmartDesk
	@OneToMany(mappedBy="bankBranch")
	private List<SmartDesk> smartDesks;


	@Transient
	private Long noOfSmartDesk;


	public BankBranch() {
		super();
		// TODO Auto-generated constructor stub
	}


	public BankBranch(Long id, String branchName, String externalId, boolean isDeleted, List<SmartDesk> smartDesks) {
		super();
		this.id = id;
		this.branchName = branchName;
		this.externalId = externalId;
		this.isDeleted = isDeleted;
		this.smartDesks = smartDesks;
	}

	public BankBranch(Long id, String branchName, String externalId,  Long noOfSmartDesk, boolean isDeleted) {
		super();
		this.id = id;
		this.branchName = branchName;
		this.externalId = externalId;
		this.isDeleted = isDeleted;
		this.noOfSmartDesk = noOfSmartDesk;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getBranchName() {
		return branchName;
	}


	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}


	public String getExternalId() {
		return externalId;
	}


	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}


	public boolean getIsDeleted() {
		return isDeleted;
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
	
	public List<SmartDesk> getSmartDesks() {
		return smartDesks;
	}


	public void setSmartDesks(List<SmartDesk> smartDesks) {
		this.smartDesks = smartDesks;
	}
}
