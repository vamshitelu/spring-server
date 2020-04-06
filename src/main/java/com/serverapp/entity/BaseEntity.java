package com.serverapp.entity;

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

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

	@CreatedBy
	@JsonIgnore
	@Column(updatable = false)
	protected String createdBy;
	
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	@JsonIgnore
	@Column(updatable = false)
	protected Date createdDate;
	
	@LastModifiedBy
	@JsonIgnore
	protected String modifiedBy;
	
	@LastModifiedDate
	@Temporal(TemporalType.TIMESTAMP)
	@JsonIgnore
	protected Date modifiedDate;
}
