package com.kaveingas.incomemanager.expense;

import java.util.List;

interface ExpenseDAO {

	List<Expense> save(List<Expense> expenses);

	Expense save(Expense expense);

	List<Expense> getByAccountId(Long accountId);
}
