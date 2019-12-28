package com.kaveingas.incomemanager.dto;

import com.kaveingas.incomemanager.address.Address;

public class UserDTO {
	
	private String uid;

	private String name;

	private String email;

	private int age;
	
	private Address address;
	
	public UserDTO() {
		this(null,null,null,0);
	}
	
	public UserDTO(String uid, String name, String email, int age) {
		this(uid,name,email,age,null);
	}

	public UserDTO(String uid, String name, String email, int age, Address address) {
		super();
		this.uid = uid;
		this.name = name;
		this.email = email;
		this.age = age;
		this.address = address;
	}



	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
}
