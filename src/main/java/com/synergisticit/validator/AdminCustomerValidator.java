package com.synergisticit.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.synergisticit.domain.Customer;
import com.synergisticit.service.CustomerService;
import com.synergisticit.service.UserService;

@Component
public class AdminCustomerValidator implements Validator {

	@Autowired
	CustomerService customerService;
	
	@Autowired
	UserService userService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		
		return Customer.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		Customer customer = (Customer) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "customerId", "empty", "Customer Id is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "empty", "Customer Name is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user.userId", "empty", "User Id is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "customerAddress.addressLine1", "empty", "Address Line 1 is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "customerAddress.city", "empty", "City is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "customerAddress.state", "empty", "State is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "empty", "Phone is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "empty", "Email is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ssn", "empty", "SSN is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dob", "empty", "Dob is required.");
		
		if (customer.getUser().getUserId() != null && !userService.existsById(customer.getUser().getUserId())) {
			errors.rejectValue("user.userId", "dne", "User Id is not recognized. Please double check.");
		}
	}

}
