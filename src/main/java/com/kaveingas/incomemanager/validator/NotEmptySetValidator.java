package com.kaveingas.incomemanager.validator;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotEmptySetValidator implements ConstraintValidator<NotEmptySet, Set<Object>> {

	@Override
	public boolean isValid(Set<Object> list, ConstraintValidatorContext context) {

		if (list == null) {
			return false;
		}

		if(list.isEmpty() || list.size()<=0) {
			return false;
		}
		
		return true;
	}

}
