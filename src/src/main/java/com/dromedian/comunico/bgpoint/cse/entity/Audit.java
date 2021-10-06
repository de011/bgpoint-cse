package com.dromedian.comunico.bgpoint.cse.entity;

import static javax.persistence.TemporalType.TIMESTAMP;
import java.util.Date;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
/**
*
* @author Waseem
*/
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Audit {

	@CreatedBy
	protected String createdBy;

	@CreatedDate
	@Temporal(TIMESTAMP)
	protected Date createdDate;

	@LastModifiedBy
	protected String lastModifiedBy;

	@LastModifiedDate
	@Temporal(TIMESTAMP)
	protected Date lastModifiedDate;

	public Audit() {
		super();
	}

	public Audit(String createdBy, Date createdDate, String lastModifiedBy, Date lastModifiedDate) {
		super();
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.lastModifiedBy = lastModifiedBy;
		this.lastModifiedDate = lastModifiedDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	@Override
	public String toString() {
		return "Audit [createdBy=" + createdBy + ", createdDate=" + createdDate + ", lastModifiedBy=" + lastModifiedBy
				+ ", lastModifiedDate=" + lastModifiedDate + "]";
	}
}