package com.example.springDataJPA.customValidator;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({  FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = {EmailValidator.class })
public @interface email {
	String message() default "Enter valid email address";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}
