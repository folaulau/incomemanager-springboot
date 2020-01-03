package com.kaveingas.incomemanager.income;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

interface IncomeRepository extends JpaRepository<Income, Long> {

	List<Income> findByUserAccountId(Long accountId);
}
