package com.kaveingas.incomemanager.spending;

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

import com.kaveingas.incomemanager.dto.SpendingCreateDTO;
import com.kaveingas.incomemanager.dto.SpendingDTO;
import com.kaveingas.incomemanager.income.Income;
import com.kaveingas.incomemanager.utils.ObjectUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "incomes", produces = "Rest API for spending operations", tags = "Spending Controller")
@RestController
@RequestMapping("/spendings")
public class SpendingController {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SpendingService spendingService;

	@ApiOperation(value = "Add spending")
	@PostMapping
	public ResponseEntity<List<SpendingDTO>> save(@RequestHeader("token") String token,
			@ApiParam(name = "accountUuid", required = true, value = "accountUuid") @RequestParam String accountUuid,
			@ApiParam(name = "spendings", required = true, value = "spendings") @Valid @RequestBody List<SpendingCreateDTO> spendings) {
		log.debug("add(..)");
		log.debug("spendings: {}", ObjectUtils.toJson(spendings));

		List<SpendingDTO> savedSpendings = spendingService.save(spendings);

		return new ResponseEntity<>(savedSpendings, HttpStatus.OK);
	}
}
