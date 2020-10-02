package com.synergisticit.controller.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.synergisticit.domain.Airline;
import com.synergisticit.domain.Airport;
import com.synergisticit.domain.Flight;
import com.synergisticit.service.AirlineService;
import com.synergisticit.service.AirportService;
import com.synergisticit.service.FlightService;
import com.synergisticit.utilities.AirlineUtilities;
import com.synergisticit.validator.AdminFlightValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class RestFlightController {

	private AirlineUtilities airUtil;
	private FlightService flightService;
	private AirlineService airlineService;
	private AirportService airportService;
	private AdminFlightValidator flightValid;
	
	public RestFlightController(AirlineUtilities airUtil, 
								FlightService flightService, 
								AirlineService airlineService,
								AirportService airportService,
								AdminFlightValidator flightValid) {
		
		this.airUtil = airUtil;
		this.flightService = flightService;
		this.airlineService = airlineService;
		this.airportService = airportService;
		this.flightValid = flightValid;
	}
	
	@InitBinder
	public void initRestFlightValid(WebDataBinder binder) {
		binder.addValidators(flightValid);
	}
	
	
	// --- MAPPINGS ---
	
	@GetMapping("/api/flight")
	public ResponseEntity<?> apiFlight() {
		log.debug("RestFlightController.apiFlight().....");
		
		List<Flight> flights = flightService.findAll();
		if (flights.isEmpty()) {
			return new ResponseEntity<String>("no flights found", HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<Flight>>(flights, HttpStatus.FOUND);
	}
	
	@GetMapping("/api/flight/{id}")
	public ResponseEntity<?> apiGetFlightById(@PathVariable("id") long id) {
		
		if (flightService.existsById(id)) {
			return new ResponseEntity<Flight>(flightService.findById(id), HttpStatus.OK);
		}
		return new ResponseEntity<String>("flight with id: " + id + " not found", HttpStatus.NOT_FOUND); 
	}
	
	@PostMapping(value = "/api/flight", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> apiSaveFlight(@Valid @RequestBody Flight flight, BindingResult br) {
		
		if (!br.hasErrors()) {
			if (!flightService.existsById(flight.getFlightId())) {
				
				airUtil.prettifyDateTimeETA(flight);
				
				return new ResponseEntity<Flight>(flightService.save(flight), HttpStatus.CREATED);
			}
			return new ResponseEntity<String>("flight id: " + flight.getFlightId() + " already exists", HttpStatus.CONFLICT);
		}
		return new ResponseEntity<String>(airUtil.getValidationErrors(br), HttpStatus.NOT_ACCEPTABLE); 
	}
	
	@PutMapping(value = "/api/flight", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> apiUpdateFlight(@Valid @RequestBody Flight flight, BindingResult br) {
		
		if (flightService.existsById(flight.getFlightId())) {
			if (!br.hasErrors()) {
				
				Flight fDb = flightService.findById(flight.getFlightId());
				
				Airline aDb = airlineService.findById(flight.getAirline().getAirlineId());
				
				Airport depart = airportService.findById(flight.getDepartFrom().getAirportId());
				Airport arrive = airportService.findById(flight.getArriveAt().getAirportId());
				
				fDb.setAirline(aDb);
				fDb.setDepartFrom(depart);
				fDb.setArriveAt(arrive);
				fDb.setDepartureDateTime(flight.getDepartureDateTime());
				fDb.setArrivalDateTime(flight.getArrivalDateTime());
				fDb.setFlightNumber(flight.getFlightNumber());
				fDb.setPrice(flight.getPrice());
				
				airUtil.prettifyDateTimeETA(fDb);
				
				return new ResponseEntity<Flight>(flightService.save(fDb), HttpStatus.OK);
				
			}
			return new ResponseEntity<String>(airUtil.getValidationErrors(br), HttpStatus.NOT_ACCEPTABLE); 
		}
		return new ResponseEntity<String>("flight with id: " + flight.getFlightId() + " not found", HttpStatus.NOT_FOUND);  
	}
	
	@DeleteMapping(value = "/api/flight/{id}")
	public ResponseEntity<?> apiDeleteFlight(@PathVariable("id") long id) {
		
		if (flightService.existsById(id)) {
			flightService.deleteById(id);
			return new ResponseEntity<String>("flight with id: " + id + " was deleted", HttpStatus.OK);  
		}
		return new ResponseEntity<String>("flight with id: " + id + " not found", HttpStatus.NOT_FOUND);   
	}
}
