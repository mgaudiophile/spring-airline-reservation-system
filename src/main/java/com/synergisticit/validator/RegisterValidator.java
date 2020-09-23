package com.synergisticit.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.synergisticit.domain.Register;
import com.synergisticit.domain.User;
import com.synergisticit.service.UserService;

//import lombok.extern.slf4j.Slf4j;

//@Slf4j
@Component
public class RegisterValidator implements Validator {

	@Autowired
	private UserService userService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		
		return Register.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		Register register = (Register) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "empty", "Name is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "addressLine1", "empty", "Address Line 1 is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "addressLine2", "empty", "Address Line 2 is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "city", "empty", "City is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "state", "empty", "State is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "zip", "empty", "Zip code is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dob", "empty", "Date of Birth is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ssn", "empty", "SSN is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gender", "empty", "Gender is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mobile", "empty", "Mobile is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "empty", "Email is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "empty", "Username is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "empty", "Password is required.");
		
		User uDb = userService.findByUsername(register.getUsername());
		if (uDb != null && userService.existsByUsername(register.getUsername())) {
			errors.rejectValue("username", "duplicate", "Username already being used.");
		}
	}

}
