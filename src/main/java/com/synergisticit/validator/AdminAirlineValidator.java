package com.synergisticit.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.synergisticit.domain.Airline;
import com.synergisticit.service.AirlineService;

@Component
public class AdminAirlineValidator implements Validator {

	@Autowired
	AirlineService airlineService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		
		return Airline.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		Airline airline = (Airline) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "airlineId", "empty", "Airline Id is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "airlineName", "empty", "Airline Name is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "airlineCode", "empty", "Airline Code is required.");
		
		if (airlineService.findByAirlineCode(airline.getAirlineCode()) != null) {
			errors.rejectValue("airlineCode", "duplicate", "Code already exists.");
		}
	}

}
