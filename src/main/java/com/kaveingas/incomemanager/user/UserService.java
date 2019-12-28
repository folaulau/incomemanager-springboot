package com.kaveingas.incomemanager.user;

import java.util.Optional;

import com.kaveingas.incomemanager.dto.SessionDTO;
import com.kaveingas.incomemanager.dto.SignupRequestDTO;

public interface UserService {
	
	User getById(Long id);
	
	User getProfileById(Long id);
	
	User getByUuid(String uid);
	
	User getByEmail(String email);

	SessionDTO signUp(SignupRequestDTO signupRequest);
}
