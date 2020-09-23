package com.synergisticit.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.synergisticit.domain.User;
import com.synergisticit.service.UserService;

@Component
public class AdminUserValidator implements Validator {

	@Autowired
	private UserService userService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		User user = (User) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userId", "user.userId.empty", "User Id is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "user.username.empty", "Name is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "user.password.empty", "Password is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "user.email.empty", "Email is required.");
		
		User uDb = userService.findByIdAndUsername(user.getUserId(), user.getUsername());
		if (uDb == null && userService.existsByUsername(user.getUsername())) {
			errors.rejectValue("username", "user.username.empty", "Username already being used.");
		}
		
		if (user.getRoles().isEmpty()) {
			errors.rejectValue("roles", "user.roles.value", "Must have at least one role");
		}
	}
}
