package com.kaveingas.incomemanager.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.kaveingas.incomemanager.utils.ValidationUtils;

public class PasswordValidator implements ConstraintValidator<Password, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {	
		return ValidationUtils.isValidPassword(value);
	}

}
