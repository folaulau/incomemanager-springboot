package com.kaveingas.incomemanager.income;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
class IncomeDAOImp implements IncomeDAO {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private IncomeRepository incomeRepository;

	@Override
	public void saveAll(List<Income> incomes) {
		incomeRepository.saveAll(incomes);
	}

	@Override
	public List<Income> getAllByAccount(Long accountId) {
		// TODO Auto-generated method stub
		return incomeRepository.findByUserAccountId(accountId);
	}

	@Override
	public Income save(Income income) {
		// TODO Auto-generated method stub
		return incomeRepository.saveAndFlush(income);
	}

}
