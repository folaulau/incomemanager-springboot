package com.kaveingas.incomemanager.validator;
import java.util.Date;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotEmptyListValidator implements ConstraintValidator<NotEmptyList, List<Object>> {

	@Override
	public boolean isValid(List<Object> list, ConstraintValidatorContext context) {

		if (list == null) {
			return false;
		}

		if(list.isEmpty() || list.size()<=0) {
			return false;
		}
		
		return true;
	}

}
