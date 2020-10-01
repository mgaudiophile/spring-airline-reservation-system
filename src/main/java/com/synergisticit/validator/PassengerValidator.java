package com.synergisticit.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.synergisticit.domain.Passenger;

@Component
public class PassengerValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		
		return Passenger.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "empty", "Name is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gender", "empty", "Gender is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dob", "empty", "Date of birth is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mobile", "empty", "Mobile is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "empty", "Email is required.");
	}

}
