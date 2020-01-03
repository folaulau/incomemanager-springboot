package com.kaveingas.incomemanager.user;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaveingas.incomemanager.dto.SignupRequestDTO;
import com.kaveingas.incomemanager.account.Account;
import com.kaveingas.incomemanager.dto.EntityDTOMapper;
import com.kaveingas.incomemanager.role.Role;
import com.kaveingas.incomemanager.role.RoleType;
import com.kaveingas.incomemanager.utils.ObjectUtils;
import com.kaveingas.incomemanager.utils.PasswordUtils;

@Service
public class UserServiceImp implements UserService {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private UserDAO userDAO;

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
	public User signUp(SignupRequestDTO signupRequest) {
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

		return user;
	}
}
