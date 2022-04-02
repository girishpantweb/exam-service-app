package com.onlineexam.app.anotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

import com.onlineexam.app.validators.MobileNumberValidator;

@Documented
@Constraint(validatedBy = MobileNumberValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface MobileValidatorConstraint {
	String message() default "Invalid phone number";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
