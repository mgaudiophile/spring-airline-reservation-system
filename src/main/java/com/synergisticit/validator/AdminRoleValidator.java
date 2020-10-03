package com.synergisticit.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.synergisticit.domain.Role;

@Component
public class AdminRoleValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		
		return Role.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "roleId", "empty", "Role id is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "roleName", "empty", "Role name is required.");
	}

}
