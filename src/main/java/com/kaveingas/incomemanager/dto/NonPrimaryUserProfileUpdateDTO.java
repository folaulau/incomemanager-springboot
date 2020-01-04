package com.kaveingas.incomemanager.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kaveingas.incomemanager.validator.Email;
import com.kaveingas.incomemanager.validator.NotEmptyString;

public class NonPrimaryUserProfileUpdateDTO {

	@NotEmptyString
	private String uuid;

	@Email
	private String email;

	@NotEmptyString
	private String firstName;

	@NotEmptyString
	private String lastName;

	private String gender;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "YYYY-MM-DD")
	private Date dateOfBirth;

	private String maritalStatus;

	// primary, spouse, parent, dependent
	private String relationToPrimary;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getRelationToPrimary() {
		return relationToPrimary;
	}

	public void setRelationToPrimary(String relationToPrimary) {
		this.relationToPrimary = relationToPrimary;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

}
