package com.onlineexam.app.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.onlineexam.app.anotations.StringLengthValidatorConstraint;
import com.onlineexam.app.utils.CommonUtility;

public class StringLengthValidator implements ConstraintValidator<StringLengthValidatorConstraint, String> {

	@Override
	public void initialize(StringLengthValidatorConstraint data) {
	}

	@Override
	public boolean isValid(String data, ConstraintValidatorContext context) {
		return CommonUtility.isValidStringLength(data);
	}

}
