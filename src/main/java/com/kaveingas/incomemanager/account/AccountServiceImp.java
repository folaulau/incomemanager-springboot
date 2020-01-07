package com.kaveingas.incomemanager.account;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImp implements AccountService {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AccountDAO accountDAO;

	@Async
	@Override
	public void saveFunnel(Long accountId, String funnel) {
		accountDAO.saveFunnel(accountId, funnel);
	}

	@Async
	@Override
	public void saveFunnel(Account account, String funnel) {
		accountDAO.save(account);
	}

}
