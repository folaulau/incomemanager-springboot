package com.kaveingas.incomemanager.expense;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

interface ExpenseRepository extends JpaRepository<Expense, Long> {

	List<Expense> findByUserAccountId(Long accountId);
}
