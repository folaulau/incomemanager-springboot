package com.kaveingas.incomemanager.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ExpenseCreateDTO {

	private String name;

	private String type;

	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "YYYY-MM-DD")
	private Date date;

	private String note;

	private double amount;

	public ExpenseCreateDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

}
