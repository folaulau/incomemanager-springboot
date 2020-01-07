package com.kaveingas.incomemanager.account;

interface AccountDAO {

	Account save(Account account);

	void saveFunnel(Long accountId, String funnel);
}
