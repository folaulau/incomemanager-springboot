package com.kaveingas.incomemanager.income;

import java.util.List;

interface IncomeDAO {

	Income save(Income income);
	
	void saveAll(List<Income> incomes);

	List<Income> getAllByAccount(Long apiSessionAccountId);

}
