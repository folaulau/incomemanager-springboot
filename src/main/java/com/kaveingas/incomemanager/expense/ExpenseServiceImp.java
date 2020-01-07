package com.kaveingas.incomemanager.expense;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaveingas.incomemanager.account.AccountService;
import com.kaveingas.incomemanager.dto.EntityDTOMapper;
import com.kaveingas.incomemanager.dto.ExpenseCreateDTO;
import com.kaveingas.incomemanager.dto.ExpenseDTO;
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

	@Autowired
	private EntityDTOMapper entityDTOMapper;
	
	@Autowired
	private AccountService accountService;

	@Override
	public List<ExpenseDTO> save(List<ExpenseCreateDTO> expenses, String funnel) {

		User user = userService.getById(ApiSessionUtils.getCurrentUserId());

		log.info("user={}", ObjectUtils.toJson(user));
		expenses.stream().forEach((expenseDTO) -> {
			
			Expense expense = entityDTOMapper.mapExpenseCreateDTOToExpense(expenseDTO);
			
			expense.setUser(user);
			
			Expense savedExpense = expenseDAO.save(expense);

			log.debug("savedExpense={}", ObjectUtils.toJson(savedExpense));
		});
		
		if(funnel!=null) {
			accountService.saveFunnel(ApiSessionUtils.getCurrentUserAccountId(), funnel);
		}

		return entityDTOMapper.mapExpensesToExpenseDTOs(expenseDAO.getByAccountId(ApiSessionUtils.getCurrentUserAccountId()));
	}

}
