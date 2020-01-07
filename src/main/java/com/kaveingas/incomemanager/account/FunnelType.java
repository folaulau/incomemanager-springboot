package com.kaveingas.incomemanager.account;

public enum FunnelType {

	SIGNUP("SIGNUP", "INCOME"),INCOME("INCOME", "EXPENSE"), EXPENSE("EXPENSE", "PROFILE"), PROFILE("PROFILE", "DONE"), DONE("DONE", "");

	private String name;
	private String next;

	FunnelType(String name, String next) {
		this.name = name;
		this.next = next;
	}

	public String getName() {
		return name;
	}

	public String getNext() {
		return next;
	}

	public static FunnelType getByName(String name) {
		for (FunnelType funnel : FunnelType.values()) {
			if (funnel.getName().equals(name)) {
				return funnel;
			}
		}
		return null;
	}

}
