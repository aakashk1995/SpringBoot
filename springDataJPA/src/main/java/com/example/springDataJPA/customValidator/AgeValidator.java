package com.example.springDataJPA.customValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AgeValidator implements ConstraintValidator<age, Integer> {

	@Override
	public boolean isValid(Integer value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		System.out.println(value);
		if(value>=18 && value<=60) 
		{
			return true;
		}
		
		return false;
	}

}