package com.kaveingas.incomemanager.account;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
class AccountDAOImp implements AccountDAO {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Account save(Account account) {
		return accountRepository.saveAndFlush(account);
	}

	@Override
	public void saveFunnel(Long accountId, String funnel) {
		
		if(FunnelType.getByName(funnel)==null) {
			log.debug("{} is not a valid funnel.", funnel);
			return;
		}
		
		StringBuilder query = new StringBuilder();
		query.append("UPDATE account ");
		query.append("SET profile_setup_status = ? ");
		query.append("WHERE id = ? ");
		
		try {
			int updateResult = jdbcTemplate.update(query.toString(), new Object[] { funnel, accountId });

			log.debug("update funnel={}", updateResult);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
}
