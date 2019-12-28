package com.kaveingas.incomemanager.user;

import org.springframework.http.HttpStatus;

import com.kaveingas.incomemanager.dto.SignupRequestDTO;
import com.kaveingas.incomemanager.exception.ApiError;
import com.kaveingas.incomemanager.exception.ApiException;
import com.kaveingas.incomemanager.utils.ValidationUtils;

public interface UserUtils {

	public static void validateSignup(UserDAO userDAO, SignupRequestDTO signupRequest) {

		if (signupRequest.getName() == null || signupRequest.getName().length() <= 0) {
			throw new ApiException(new ApiError(HttpStatus.BAD_REQUEST, "Name is required",
					"name is empty. name=" + signupRequest.getName()));
		}

		if (ValidationUtils.isValidPassword(signupRequest.getPassword())) {
			throw new ApiException(new ApiError(HttpStatus.BAD_REQUEST, "Password is invalid",
					"invalid password. password=" + signupRequest.getPassword()));
		}

		if (signupRequest.getAge() <= 12) {
			throw new ApiException(new ApiError(HttpStatus.BAD_REQUEST, "Invalid age",
					"age is less than 12. age=" + signupRequest.getAge()));
		}

		if (ValidationUtils.isValidEmail(signupRequest.getEmail())) {
			throw new ApiException(new ApiError(HttpStatus.BAD_REQUEST, "Email is invalid",
					"email is invalid. email=" + signupRequest.getEmail()));
		}

		if (userDAO.doesEmailExist(signupRequest.getEmail())) {
			throw new ApiException(new ApiError(HttpStatus.BAD_REQUEST, "Email taken",
					"email is taken. email=" + signupRequest.getEmail()));
		}
	}
}
