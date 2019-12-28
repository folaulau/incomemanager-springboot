package com.kaveingas.incomemanager.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.kaveingas.incomemanager.utils.HttpUtils;
import com.kaveingas.incomemanager.utils.ObjectUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "mocks",produces = "Rest API for mock operations", tags = "Mock Controller")
@RestController
@RequestMapping("/tests")
public class MockController {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	
	@ApiOperation(value = "Get Basic Auth Token")
	@PostMapping("/generate-basic-auth-token")
	public ResponseEntity<ObjectNode> generateBasicAuthToken(@RequestParam("email") String email,@RequestParam("password") String password ) {
		log.info("generateBasicAuthToken");
		ObjectNode auth = ObjectUtils.getObjectNode();
		String authToken = HttpUtils.generateBasicAuthenticationToken(email, password);
		auth.put("token", authToken);
		return new ResponseEntity<>(auth, HttpStatus.OK);
	}
}
