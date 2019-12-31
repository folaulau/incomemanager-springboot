package com.kaveingas.incomemanager.dto;

import com.kaveingas.incomemanager.validator.Email;
import com.kaveingas.incomemanager.validator.NotEmptyString;
import com.kaveingas.incomemanager.validator.Password;

public class SignupRequestDTO {

	@NotEmptyString(message = "first name is required")
	private String firstName;

	@NotEmptyString(message = "last name is required")
	private String lastName;

	@Email(message = "email is required")
	private String email;

	@Password
	private String password;

	public SignupRequestDTO() {
		super();
		// TODO Auto-generated constructor stub
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
