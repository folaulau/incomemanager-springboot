package com.kaveingas.incomemanager.security.auth;

import javax.servlet.http.HttpServletRequest;

import com.kaveingas.incomemanager.dto.SessionDTO;
import com.kaveingas.incomemanager.user.User;

public interface AuthenticationService {

	SessionDTO authenticate(User user, HttpServletRequest request);
	
}
