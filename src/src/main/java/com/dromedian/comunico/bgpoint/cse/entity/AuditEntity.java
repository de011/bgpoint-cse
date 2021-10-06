package com.dromedian.comunico.bgpoint.cse.entity;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditEntity<user> {

	@Column(name = "created_on", nullable = false, updatable = false)
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    protected Date createdOn;

	@Column(name="created_by")
    @CreatedBy
	private user createdBy;	
	
	@Column(name="modified_on")
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    protected Date modifiedOn;	
	@Column(name="modified_by")
    @LastModifiedBy
	private user modifiedBy;

    public AuditEntity(){
    	
    }

    
}