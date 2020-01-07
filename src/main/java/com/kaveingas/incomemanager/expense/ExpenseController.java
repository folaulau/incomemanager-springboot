package com.kaveingas.incomemanager.expense;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kaveingas.incomemanager.dto.EntityDTOMapper;
import com.kaveingas.incomemanager.dto.ExpenseCreateDTO;
import com.kaveingas.incomemanager.dto.ExpenseDTO;
import com.kaveingas.incomemanager.income.Income;
import com.kaveingas.incomemanager.utils.ObjectUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "expenses", produces = "Rest API for expense operations", tags = "Expense Controller")
@RestController
@RequestMapping("/expenses")
public class ExpenseController {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ExpenseService expenseService;


	@ApiOperation(value = "Add Expenses")
	@PostMapping
	public ResponseEntity<List<ExpenseDTO>> save(
			@RequestHeader("token") String token,
			@ApiParam(name = "funnel", required = false, value = "funnel") @RequestParam(required = false, name = "funnel") String funnel,
			@ApiParam(name = "expenses", required = true, value = "expenses") @Valid @RequestBody List<ExpenseCreateDTO> expenses) {
		log.debug("add(..)");
		log.debug("expenses: {}", ObjectUtils.toJson(expenses));

		List<ExpenseDTO> savedExpenses = expenseService.save(expenses, funnel);

		return new ResponseEntity<>(savedExpenses, HttpStatus.OK);
	}

}
