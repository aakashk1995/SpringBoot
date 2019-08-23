package com.example.springDataJPA.customValidator;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;




@Target({  FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = {AgeValidator.class })
public @interface age {
	
	String message() default "age must be between 18 and 60";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
	
	
}
