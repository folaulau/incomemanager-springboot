package com.kaveingas.incomemanager.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImp implements UserDAO {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private UserRepository userRepository;

	@Override
	public User save(User user) {
		// TODO Auto-generated method stub
		return userRepository.saveAndFlush(user);
	}

	@Override
	public User getByEmail(String email) {
		// TODO Auto-generated method stub
		return userRepository.findByEmail(email).orElse(null);
	}

	@Override
	public User getById(Long id) {
		// TODO Auto-generated method stub
		return userRepository.getById(id);
	}

	@Override
	public User getByUuid(String uuid) {
		// TODO Auto-generated method stub
		return userRepository.findByUuid(uuid).orElse(null);
	}

	@Override
	public boolean doesEmailExist(String email) {
		
		return userRepository.existsUserByEmail(email);
	}

}
