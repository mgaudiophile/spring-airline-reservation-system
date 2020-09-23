package com.synergisticit.validator;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.synergisticit.domain.Airport;
//import com.synergisticit.service.AirportService;

@Component
public class AdminAirportValidator implements Validator {

	//@Autowired
	//private AirportService airportService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		
		return Airport.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		//Airport airport = (Airport) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "airportId", "empty", "Airport Id is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "airportName", "airport.airportName.empty", "Airport Name is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "airportCode", "empty", "Airport Code is required.");
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "airportAddress.addressLine1", "empty", "Address Line 1 is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "airportCity", "empty", "City is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "airportState", "empty", "State is required.");
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "airportPhone", "empty", "Phone is required.");
		
		
	}

}
