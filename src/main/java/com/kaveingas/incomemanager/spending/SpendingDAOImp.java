package com.kaveingas.incomemanager.spending;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
class SpendingDAOImp implements SpendingDAO {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private SpendingRepository spendingRepository;

	@Override
	public Spending save(Spending spending) {
		// TODO Auto-generated method stub
		return spendingRepository.saveAndFlush(spending);
	}

	@Override
	public List<Spending> save(List<Spending> spendings) {
		// TODO Auto-generated method stub
		return spendingRepository.saveAll(spendings);
	}
}
