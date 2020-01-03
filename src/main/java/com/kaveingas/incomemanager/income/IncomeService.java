package com.kaveingas.incomemanager.income;

import java.util.List;

import javax.validation.Valid;

public interface IncomeService {

	List<Income> save(List<Income> incomes);

}
