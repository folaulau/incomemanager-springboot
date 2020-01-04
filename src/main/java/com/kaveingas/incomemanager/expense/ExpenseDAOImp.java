package com.kaveingas.incomemanager.expense;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
class ExpenseDAOImp implements ExpenseDAO {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private ExpenseRepository expenseRepository;

	@Override
	public List<Expense> save(List<Expense> expenses) {
		return expenseRepository.saveAll(expenses);
	}

	@Override
	public Expense save(Expense expense) {
		// TODO Auto-generated method stub
		return expenseRepository.saveAndFlush(expense);
	}

	@Override
	public List<Expense> getByAccountId(Long accountId) {
		// TODO Auto-generated method stub
		return expenseRepository.findByUserAccountId(accountId);
	}

}
