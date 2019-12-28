package com.kaveingas.incomemanager.user;

import java.util.Optional;

import com.kaveingas.incomemanager.dto.SessionDTO;
import com.kaveingas.incomemanager.dto.SignupRequestDTO;

public interface UserService {
	
	User create(User user);
	
	User getById(Long id);
	
	User getProfileById(Long id);
	
	User getByUid(String uid);
	
	Optional<User> findByUid(String uid);

	Optional<User> findByEmail(String email);
	
	User getByEmail(String email);

	SessionDTO signUp(SignupRequestDTO signupRequest);
}
