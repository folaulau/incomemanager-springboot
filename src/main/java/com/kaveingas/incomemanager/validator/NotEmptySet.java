package com.kaveingas.incomemanager.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NotEmptySetValidator.class)
public @interface NotEmptySet {

	String message() default "list is empty";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
