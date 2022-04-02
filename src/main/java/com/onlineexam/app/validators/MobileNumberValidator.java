package com.onlineexam.app.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.onlineexam.app.anotations.MobileValidatorConstraint;
import com.onlineexam.app.utils.CommonUtility;

public class MobileNumberValidator implements ConstraintValidator<MobileValidatorConstraint, String> {

	@Override
	public void initialize(MobileValidatorConstraint contactNumber) {
	}

	@Override
	public boolean isValid(String mobileNumber, ConstraintValidatorContext context) {
		return CommonUtility.isValidMobileNumber(mobileNumber);
	}
}
