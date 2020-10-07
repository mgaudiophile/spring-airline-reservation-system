package com.synergisticit.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.synergisticit.domain.Flight;
import com.synergisticit.service.AirlineService;
import com.synergisticit.service.AirportService;

@Component
public class AdminFlightValidator implements Validator {

	@Autowired
	AirportService airportService;
	
	@Autowired
	AirlineService airlineService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		
		return Flight.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		Flight flight = (Flight) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "flightId", "empty", "Flight Id is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "flightNumber", "empty", "Flight Number is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "airline.airlineId", "empty", "Airline Id is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "departFrom.airportId", "empty", "Departure Airport Id is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "arriveAt.airportId", "empty", "Arrival Airport Id is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "departureDateTime", "empty", "Departure Date Time is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "arrivalDateTime", "empty", "Arrival Date Time is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "empty", "price is required.");
		
		if (flight.getAirline().getAirlineId() != null && !airlineService.existsById(flight.getAirline().getAirlineId())) {
			errors.rejectValue("airline.airlineId", "dne", "Airline Id does not exist");
		}
		
		if (flight.getDepartFrom().getAirportId() != null && !airportService.existsById(flight.getDepartFrom().getAirportId())) {
			errors.rejectValue("departFrom.airportId", "dne", "Airport Id does not exist");
		}
		
		if (flight.getArriveAt().getAirportId() != null && !airportService.existsById(flight.getArriveAt().getAirportId())) {
			errors.rejectValue("arriveAt.airportId", "dne", "Airport Id does not exist");
		}
		
		if (flight.getDepartFrom().getAirportId() != null && flight.getArriveAt().getAirportId() != null && 
				flight.getDepartFrom().getAirportId() == flight.getArriveAt().getAirportId()) {
			errors.rejectValue("arriveAt.airportId", "dup", "Choose a different airport.");
		}
	}

}
