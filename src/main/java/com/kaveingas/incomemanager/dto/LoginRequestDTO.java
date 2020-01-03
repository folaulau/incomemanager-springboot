package com.kaveingas.incomemanager.dto;

import java.io.IOException;

import com.kaveingas.incomemanager.utils.ObjectUtils;
import com.kaveingas.incomemanager.validator.Email;
import com.kaveingas.incomemanager.validator.Password;

public class LoginRequestDTO {

	@Email(message = "email is required")
	private String email;

	@Password
	private String password;

	public LoginRequestDTO() {
		super();
		// TODO Auto-generated constructor stub
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

	public static LoginRequestDTO fromJson(String json) {
		try {
			return ObjectUtils.getObjectMapper().readValue(json, LoginRequestDTO.class);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("JsonProcessingException, msg: " + e.getLocalizedMessage());
			return null;
		}
	}
	
}
