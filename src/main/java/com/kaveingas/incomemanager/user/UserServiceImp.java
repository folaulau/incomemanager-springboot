package com.kaveingas.incomemanager.user;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaveingas.incomemanager.dto.SessionDTO;
import com.kaveingas.incomemanager.dto.SignupRequestDTO;
import com.kaveingas.incomemanager.account.Account;
import com.kaveingas.incomemanager.dto.EntityDTOMapper;
import com.kaveingas.incomemanager.jwt.JwtPayload;
import com.kaveingas.incomemanager.jwt.JwtTokenUtils;
import com.kaveingas.incomemanager.role.Role;
import com.kaveingas.incomemanager.role.RoleType;
import com.kaveingas.incomemanager.utils.HttpUtils;
import com.kaveingas.incomemanager.utils.ObjectUtils;
import com.kaveingas.incomemanager.utils.PasswordUtils;
import com.kaveingas.incomemanager.utils.RandomGeneratorUtils;

@Service
public class UserServiceImp implements UserService {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private EntityDTOMapper userMapper;

	@Override
	public User getById(Long id) {
		// TODO Auto-generated method stub
		return userDAO.getById(id);
	}

	@Override
	public User getProfileById(Long id) {
		// TODO Auto-generated method stub
		return userDAO.getById(id);
	}

	@Override
	public User getByUuid(String uuid) {
		// TODO Auto-generated method stub
		return userDAO.getByUuid(uuid);
	}

	@Override
	public User getByEmail(String email) {
		// TODO Auto-generated method stub
		return userDAO.getByEmail(email);
	}

	@Override
	public SessionDTO signUp(SignupRequestDTO signupRequest) {
		log.debug("signup(..)");
		UserUtils.validateSignup(userDAO, signupRequest);
		
		User user = this.userMapper.signupRequestToUser(signupRequest);
		user.setAccount(new Account());
		
		Role role = new Role();
		role.setAuthority(RoleType.USER);
		role.addUser(user);
		user.addRole(role);

		user.setPassword(PasswordUtils.hashPassword(user.getPassword()));
		log.debug("user: {}", ObjectUtils.toJson(user));

		user = userDAO.save(user);

		log.debug("saved user: {}", ObjectUtils.toJson(user));
		JwtPayload jwtpayload = new JwtPayload(user, RandomGeneratorUtils.getJwtUuid());

		String clientUserAgent = HttpUtils.getRequestUserAgent(request);

		jwtpayload.setDeviceId(clientUserAgent);

		log.debug("jwtpayload: {}", ObjectUtils.toJson(jwtpayload));

		String jwtToken = JwtTokenUtils.generateToken(jwtpayload);

		SessionDTO ssnDto = new SessionDTO();
		ssnDto.setEmail(user.getEmail());
		ssnDto.setUserUuid(user.getUuid());
		ssnDto.setToken(jwtToken);

		return ssnDto;
	}
}
