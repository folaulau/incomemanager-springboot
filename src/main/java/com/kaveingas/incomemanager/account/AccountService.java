package com.kaveingas.incomemanager.account;

public interface AccountService {

	void saveFunnel(Long accountId, String funnel);

	void saveFunnel(Account account, String funnel);
}
