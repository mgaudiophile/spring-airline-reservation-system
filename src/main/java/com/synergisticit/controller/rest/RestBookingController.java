package com.synergisticit.controller.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.synergisticit.domain.Passenger;
import com.synergisticit.domain.Payment;
import com.synergisticit.domain.Ticket;
import com.synergisticit.service.FlightService;
import com.synergisticit.service.PassengerService;
import com.synergisticit.utilities.AirlineUtilities;
import com.synergisticit.validator.PassengerValidator;
import com.synergisticit.validator.PaymentValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class RestBookingController {

	private AirlineUtilities airUtil;
	private PassengerService passengerService;
	private PassengerValidator passengerValid;
	private PaymentValidator paymentValid;
	private FlightService flightService;
	
	public RestBookingController(AirlineUtilities airUtil,
								PassengerService passengerService,
								PassengerValidator passengerValid,
								PaymentValidator paymentValid,
								FlightService flightService) {
		this.airUtil = airUtil;
		this.passengerService = passengerService;
		this.passengerValid = passengerValid;
		this.paymentValid = paymentValid;
		this.flightService = flightService;
	}
	
	@InitBinder("passenger")
	public void initRestPassengerValidBinder(WebDataBinder binder) {
		binder.addValidators(passengerValid);
	}
	
	@InitBinder("payment")
	public void initRestPaymentValidBinder(WebDataBinder binder) {
		binder.addValidators(paymentValid);
	}
	
	
	// --- MAPPINGS ---
	
	@GetMapping("/api/booking/passenger")
	public ResponseEntity<?> apiGetPassengers() {
		
		List<Passenger> passengers = passengerService.findAll();
		if (passengers.isEmpty()) {
			return new ResponseEntity<String>("no passengers found", HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<Passenger>>(passengers, HttpStatus.FOUND);
	}
	
	@PostMapping(value = "/api/booking/passenger", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> apiSavePassenger(@RequestBody @Valid Passenger passenger, BindingResult br, Errors error, Model model) {
		
		if (!br.hasErrors()) {
			airUtil.addPassenger(passenger);
			log.debug("passengers.size(): " + airUtil.getPassengersSize());
			return new ResponseEntity<Passenger>(passenger, HttpStatus.OK);
		}
		return new ResponseEntity<String>(airUtil.getValidationErrors(br), HttpStatus.NOT_ACCEPTABLE); 
	}
	
	@PostMapping(value = "/api/booking/purchase", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> apiPurchase(@RequestBody @Valid Payment payment, BindingResult br, @RequestParam long customerId, @RequestParam long flightId) {
		
		if (!br.hasErrors()) {
			if (flightService.existsById(flightId)) {
				Ticket tDb = airUtil.restPurchaseTicket(payment, customerId, flightId);
				tDb.setPassengers(passengerService.findAllByTicketId(tDb.getTicketId()));
				return new ResponseEntity<Ticket>(tDb, HttpStatus.ACCEPTED);
			}
			return new ResponseEntity<String>("flight id: " + flightId + " not found", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>(airUtil.getValidationErrors(br), HttpStatus.NOT_ACCEPTABLE); 
	}
}
