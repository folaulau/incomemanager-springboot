package com.kaveingas.incomemanager.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import com.kaveingas.incomemanager.dto.SignupRequestDTO;
import com.kaveingas.incomemanager.exception.ApiError;
import com.kaveingas.incomemanager.exception.ApiException;
import com.kaveingas.incomemanager.utils.ValidationUtils;

public interface UserUtils {
	
	Logger log = LoggerFactory.getLogger(UserUtils.class);

	public static void validateSignup(UserDAO userDAO, SignupRequestDTO signupRequest) {
		log.info("validateSignup(..)");
		
		if (signupRequest.getFirstName() == null || signupRequest.getFirstName().length() <= 0) {
			throw new ApiException(new ApiError(HttpStatus.BAD_REQUEST, "First Name is required",
					"first name is empty. first name=" + signupRequest.getFirstName()));
		}
		
		if (signupRequest.getLastName() == null || signupRequest.getLastName().length() <= 0) {
			throw new ApiException(new ApiError(HttpStatus.BAD_REQUEST, "Last Name is required",
					"last name is empty. last name=" + signupRequest.getLastName()));
		}

		if (ValidationUtils.isValidPassword(signupRequest.getPassword())==false) {
			throw new ApiException(new ApiError(HttpStatus.BAD_REQUEST, "Password is invalid",
					"invalid password. password=" + signupRequest.getPassword()));
		}

		if (ValidationUtils.isValidEmail(signupRequest.getEmail())==false) {
			throw new ApiException(new ApiError(HttpStatus.BAD_REQUEST, "Email is invalid",
					"email is invalid. email=" + signupRequest.getEmail()));
		}

		if (userDAO.doesEmailExist(signupRequest.getEmail())) {
			throw new ApiException(new ApiError(HttpStatus.BAD_REQUEST, "Email taken",
					"email is taken. email=" + signupRequest.getEmail()));
		}
	}
}
