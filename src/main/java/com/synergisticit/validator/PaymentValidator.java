package com.synergisticit.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.synergisticit.domain.Payment;

@Component
public class PaymentValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		
		return Payment.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "empty", "Name is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "billingAddress.addressLine1", "empty", "Address Line 1 is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "billingAddress.addressLine2", "empty", "Address Line 2 is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "billingAddress.city", "empty", "City is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "billingAddress.state", "empty", "State is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "billingAddress.zip", "empty", "Zip code is required.");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "creditCardNumber", "empty", "Credit card number is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "expiration", "empty", "Expiration is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ccv", "empty", "CCV is required.");
	}

}
