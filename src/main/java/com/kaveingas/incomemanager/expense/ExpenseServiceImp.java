package com.kaveingas.incomemanager.expense;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaveingas.incomemanager.income.Income;
import com.kaveingas.incomemanager.user.User;
import com.kaveingas.incomemanager.user.UserService;
import com.kaveingas.incomemanager.utils.ApiSessionUtils;
import com.kaveingas.incomemanager.utils.ObjectUtils;

@Service
public class ExpenseServiceImp implements ExpenseService {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ExpenseDAO expenseDAO;

	@Autowired
	private UserService userService;

	@Override
	public List<Expense> save(List<Expense> expenses) {

		User user = userService.getById(ApiSessionUtils.getCurrentUserId());

		log.info("user={}", ObjectUtils.toJson(user));
		expenses.stream().forEach((expense) -> {
			expense.setUser(user);
			Expense savedExpense = expenseDAO.save(expense);

			log.info("savedExpense={}", ObjectUtils.toJson(savedExpense));
		});

		return expenseDAO.getByAccountId(ApiSessionUtils.getCurrentUserAccountId());
	}

}
