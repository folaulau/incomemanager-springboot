package com.kaveingas.incomemanager.user;

import java.util.Optional;

import com.kaveingas.incomemanager.dto.AccountStatsDTO;
import com.kaveingas.incomemanager.dto.SessionDTO;
import com.kaveingas.incomemanager.dto.SignupRequestDTO;
import com.kaveingas.incomemanager.dto.UserProfileDTO;
import com.kaveingas.incomemanager.dto.UserProfileUpdateDTO;

public interface UserService {
	
	User getById(Long id);
	
	User getProfileById(Long id);
	
	User getByUuid(String uid);
	
	User findByUuid(String uid);
	
	User getByEmail(String email);

	User signUp(SignupRequestDTO signupRequest);

	UserProfileDTO updateProfile(UserProfileUpdateDTO userProfileUpdateDTO);

	UserProfileDTO getProfile(String uuid);

	AccountStatsDTO getStats(String accountUuid);
}
