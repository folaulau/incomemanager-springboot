package com.kaveingas.incomemanager.income;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.kaveingas.incomemanager.spending.SpendingItem;
import com.kaveingas.incomemanager.utils.ApiSessionUtils;
import com.kaveingas.incomemanager.utils.RandomGeneratorUtils;

@JsonInclude(value = Include.NON_NULL)
@Entity
@Where(clause = "deleted = 'F'")
@Table(name = "income", indexes = { @Index(columnList = "uuid") })
public class Income implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false, unique = true)
	private Long id;

	@Column(name = "uuid", updatable = false, nullable = false, unique = true)
	private String uuid;

	@Column(name = "company_name")
	private String companyName;

	// WEEKLY, BI WEEKLY, MONTHLY
	@Column(name = "recurrence")
	private String recurrence;

	@Column(name = "paycheck_net_amount")
	private double paycheckNetAmount;

	@Type(type = "true_false")
	@Column(name = "deleted", columnDefinition = "char(1) default 'F'")
	private boolean deleted;

	@Column(name = "deleted_by")
	private Long deletedBy;

	@Column(name = "deleted_at")
	private Date deletedAt;

	@CreationTimestamp
	@Column(name = "created_at", updatable = false)
	private Date createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at", updatable = true)
	private Date updatedAt;

	@Column(name = "created_by")
	private Long createdBy;

	@Column(name = "updated_by")
	private Long updatedBy;

	public Income() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getRecurrence() {
		return recurrence;
	}

	public void setRecurrence(String recurrence) {
		this.recurrence = recurrence;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public Long getDeletedBy() {
		return deletedBy;
	}

	public void setDeletedBy(Long deletedBy) {
		this.deletedBy = deletedBy;
	}

	public Date getDeletedAt() {
		return deletedAt;
	}

	public void setDeletedAt(Date deletedAt) {
		this.deletedAt = deletedAt;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public double getPaycheckNetAmount() {
		return paycheckNetAmount;
	}

	public void setPaycheckNetAmount(double paycheckNetAmount) {
		this.paycheckNetAmount = paycheckNetAmount;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37).append(this.getId()).append(this.getUuid()).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (obj.getClass() != getClass()) {
			return false;
		}
		SpendingItem other = (SpendingItem) obj;
		return new EqualsBuilder().append(this.getId(), other.getId()).append(this.getUuid(), other.getUuid())
				.isEquals();
	}

	@PrePersist
	private void preCreate() {
		this.uuid = RandomGeneratorUtils.getIncomeUuid();
		long currentUserId = ApiSessionUtils.getApiSessionUserId();

		if (currentUserId > 0) {
			this.createdBy = currentUserId;
		}
	}

	@PreUpdate
	private void preUpdate() {

		long currentUserId = ApiSessionUtils.getApiSessionUserId();

		if (currentUserId > 0) {
			this.updatedBy = currentUserId;
		}

	}
}
