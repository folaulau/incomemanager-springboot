package com.kaveingas.incomemanager.dto;

import javax.validation.constraints.Min;

import com.kaveingas.incomemanager.validator.Email;
import com.kaveingas.incomemanager.validator.NotEmptyString;
import com.kaveingas.incomemanager.validator.Password;

public class SignupRequestDTO {

	@NotEmptyString(message = "name is required")
    private String name;

    @Email(message = "email is required")
    private String email;

    @Min(value = 12, message = "age is invalid")
    private int age;

    @Password
    private String password;

	public SignupRequestDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
    
    

    
}
