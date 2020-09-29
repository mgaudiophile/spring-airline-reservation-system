package com.synergisticit.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.synergisticit.domain.Profile;

@Component
public class ProfileValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		
		return Profile.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "addressLine1", "empty", "Address Line 1 is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "addressLine2", "empty", "Address Line 2 is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "city", "empty", "City is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "state", "empty", "State is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "zip", "empty", "Zip code is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mobile", "empty", "Mobile is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "empty", "Email is required.");
	}

}
