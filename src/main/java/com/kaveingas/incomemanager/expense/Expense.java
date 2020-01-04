package com.kaveingas.incomemanager.expense;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
import com.kaveingas.incomemanager.spending.Spending;
import com.kaveingas.incomemanager.user.User;
import com.kaveingas.incomemanager.utils.ApiSessionUtils;
import com.kaveingas.incomemanager.utils.RandomGeneratorUtils;

/**
 * Plan or Schedule monthly expenses
 */
@JsonInclude(value = Include.NON_NULL)
@Entity
@Where(clause = "deleted = 'F'")
@Table(name = "expense", indexes = { @Index(columnList = "uuid"), @Index(columnList = "user_id"), @Index(columnList = "amount") })
public class Expense implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false, unique = true)
	private Long id;

	@Column(name = "uuid", updatable = false, nullable = false, unique = true)
	private String uuid;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@Column(name = "name")
	private String name;

	@Column(name = "type")
	private String type;

	@Column(name = "took_place_at")
	private Date date;

	@Lob
	private String note;

	@Column(name = "amount")
	private double amount;

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

	public Expense() {
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
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
		Spending other = (Spending) obj;
		return new EqualsBuilder().append(this.getId(), other.getId()).append(this.getUuid(), other.getUuid())
				.isEquals();
	}

	@PrePersist
	private void preCreate() {
		this.uuid = RandomGeneratorUtils.getExpenseUuid();
		long currentUserId = ApiSessionUtils.getCurrentUserId();

		if (currentUserId > 0) {
			this.createdBy = currentUserId;
		}
	}

	@PreUpdate
	private void preUpdate() {

		long currentUserId = ApiSessionUtils.getCurrentUserId();

		if (currentUserId > 0) {
			this.updatedBy = currentUserId;
		}

	}
}
