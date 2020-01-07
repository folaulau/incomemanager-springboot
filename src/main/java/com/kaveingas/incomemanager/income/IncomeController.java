package com.kaveingas.incomemanager.income;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.kaveingas.incomemanager.dto.SessionDTO;
import com.kaveingas.incomemanager.dto.SignupRequestDTO;
import com.kaveingas.incomemanager.dto.UserDTO;
import com.kaveingas.incomemanager.dto.EntityDTOMapper;
import com.kaveingas.incomemanager.dto.IncomeCreateDTO;
import com.kaveingas.incomemanager.dto.LoginRequestDTO;
import com.kaveingas.incomemanager.role.Role;
import com.kaveingas.incomemanager.role.RoleType;
import com.kaveingas.incomemanager.security.auth.AuthenticationService;
import com.kaveingas.incomemanager.utils.HttpUtils;
import com.kaveingas.incomemanager.utils.ObjectUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "incomes", produces = "Rest API for income operations", tags = "Income Controller")
@RestController
@RequestMapping("/incomes")
public class IncomeController {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IncomeService incomeService;

	@Autowired
	private EntityDTOMapper entityDTOMapper;

	@Autowired
	private AuthenticationService authenticationService;

	/**
	 * 
	 * @param apiKey
	 * @param user
	 * @return
	 */

	@ApiOperation(value = "Add incomes")
	@PostMapping
	public ResponseEntity<List<Income>> save(@RequestHeader("token") String token,
			@ApiParam(name = "funnel", required = false, value = "funnel") @RequestParam(required = false, name = "funnel") String funnel,
			@ApiParam(name = "incomes", required = true, value = "incomes") @Valid @RequestBody List<Income> incomes) {
		log.debug("add(..)");
		log.debug("incomes: {}", ObjectUtils.toJson(incomes));

		List<Income> savedIncomes = incomeService.save(incomes, funnel);

		return new ResponseEntity<>(savedIncomes, HttpStatus.OK);
	}

}
