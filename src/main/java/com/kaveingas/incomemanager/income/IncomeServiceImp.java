package com.kaveingas.incomemanager.income;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaveingas.incomemanager.account.AccountService;
import com.kaveingas.incomemanager.user.User;
import com.kaveingas.incomemanager.user.UserService;
import com.kaveingas.incomemanager.utils.ApiSessionUtils;
import com.kaveingas.incomemanager.utils.ObjectUtils;

@Service
public class IncomeServiceImp implements IncomeService {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private IncomeDAO incomeDAO;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AccountService accountService;

	@Override
	public List<Income> save(List<Income> incomes, String funnel) {
		log.info("user session id={}",ApiSessionUtils.getCurrentUserId());
		User user = userService.getById(ApiSessionUtils.getCurrentUserId());
		
		log.info("user={}",ObjectUtils.toJson(user));
		incomes.stream().forEach((income)->{
			income.setUser(user);
			Income savedIncome = incomeDAO.save(income);
			
			log.info("savedIncome={}",ObjectUtils.toJson(savedIncome));
		});
		
		if(funnel!=null) {
			accountService.saveFunnel(ApiSessionUtils.getCurrentUserAccountId(), funnel);
		}
		
		return incomeDAO.getAllByAccount(ApiSessionUtils.getCurrentUserAccountId());
	}
	
}
