package com.kaveingas.incomemanager.expense;

import java.util.List;

import com.kaveingas.incomemanager.dto.ExpenseCreateDTO;
import com.kaveingas.incomemanager.dto.ExpenseDTO;

public interface ExpenseService {

	List<ExpenseDTO> save(List<ExpenseCreateDTO> expenses, String funnel);
}
