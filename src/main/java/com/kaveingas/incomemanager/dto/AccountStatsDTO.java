package com.kaveingas.incomemanager.dto;

public class AccountStatsDTO {

	private double totalIncome;
	private double totalEstimatedExpense;
	private double totalSpending;
	private double totalSaving;
	private String life;
	private double lifePercentage;
	private String status;

	public AccountStatsDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public double getTotalIncome() {
		return totalIncome;
	}

	public void setTotalIncome(double totalIncome) {
		this.totalIncome = totalIncome;
	}

	public double getTotalEstimatedExpense() {
		return totalEstimatedExpense;
	}

	public void setTotalEstimatedExpense(double totalEstimatedExpense) {
		this.totalEstimatedExpense = totalEstimatedExpense;
	}

	public double getTotalSpending() {
		return totalSpending;
	}

	public void setTotalSpending(double totalSpending) {
		this.totalSpending = totalSpending;
	}

	public double getTotalSaving() {
		return totalSaving;
	}

	public void setTotalSaving(double totalSaving) {
		this.totalSaving = totalSaving;
	}

	public String getLife() {
		return life;
	}

	public void setLife(String life) {
		this.life = life;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getLifePercentage() {
		return lifePercentage;
	}

	public void setLifePercentage(double lifePercentage) {
		this.lifePercentage = lifePercentage;
	}

}
