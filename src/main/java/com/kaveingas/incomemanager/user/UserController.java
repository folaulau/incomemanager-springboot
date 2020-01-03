package com.kaveingas.incomemanager.user;

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
import com.kaveingas.incomemanager.dto.LoginRequestDTO;
import com.kaveingas.incomemanager.role.Role;
import com.kaveingas.incomemanager.role.RoleType;
import com.kaveingas.incomemanager.security.auth.AuthenticationService;
import com.kaveingas.incomemanager.utils.HttpUtils;
import com.kaveingas.incomemanager.utils.ObjectUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "users",produces = "Rest API for user operations", tags = "User Controller")
@RestController
@RequestMapping("/users")
public class UserController {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserService userService;
	
	
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
	
	@ApiOperation(value = "Sign Up")
	@PostMapping("/signup")
	public ResponseEntity<SessionDTO> signUp(@ApiParam(name="user", required=true, value="user") @Valid @RequestBody SignupRequestDTO signupRequest,HttpServletRequest request){
		log.debug("signUp(..)");
		log.debug("signupRequest: {}",ObjectUtils.toJson(signupRequest));
		User user = userService.signUp(signupRequest);
		
		SessionDTO userSession = authenticationService.authenticate(user, request);
		log.debug("userSession: {}",ObjectUtils.toJson(userSession));
		
		return new ResponseEntity<>(userSession, HttpStatus.OK);
	}
	
	/**
	 * This method is for show only. It does not get called on login.
	 * Check CustomLoginFilter.java - Spring security set this up.
	 * 
	 * @param apiKey
	 * @param user
	 * @return
	 */
	
	@ApiOperation(value = "Login")
	@PostMapping("/login")
	public ResponseEntity<SessionDTO> login(@ApiParam(name="authorization", required=true, value="authorization") @Valid @RequestBody LoginRequestDTO loginRequestDTO){
		log.info("login(...)");
		return new ResponseEntity<>(new SessionDTO(), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Logout")
	@DeleteMapping("/logout")
	public ResponseEntity<?> logout(@RequestHeader("token") String token){
		log.info("logout(...)");
		
		ObjectNode result = ObjectUtils.getObjectNode();
		result.put("status", "good");
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	

	
	@Secured(value={RoleType.ROLE_USER})
	@ApiOperation(value = "Get Member By Uuid")
	@GetMapping("/users/{uuid}")
	public ResponseEntity<UserDTO> getUserByUuid(@RequestHeader(name="token", required=true) String token, @ApiParam(name="uuid", required=true, value="uuid") @PathVariable("uuid") String uuid){
		log.debug("getUserByUuid(..)");
		
		User user = userService.getByUuid(uuid);
		
		UserDTO userDto = entityDTOMapper.userToUserDTO(user);
		
		log.debug("userDto: {}",ObjectUtils.toJson(userDto));
		
		return new ResponseEntity<>(userDto, HttpStatus.OK);
	}
}
