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

import com.synergisticit.domain.Airport;
import com.synergisticit.service.AirportService;
import com.synergisticit.utilities.AirlineUtilities;
import com.synergisticit.validator.AdminAirportValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class RestAirportController {

	private AirlineUtilities airUtil;
	private AirportService airportService;
	private AdminAirportValidator airportValid;
	
	public RestAirportController(AirlineUtilities airUtil, 
									AirportService airportService,
									AdminAirportValidator airportValid) {
		this.airUtil = airUtil;
		this.airportService = airportService;
		this.airportValid = airportValid;
	}
	
	@InitBinder
	public void initAirportValidator(WebDataBinder binder) {
		binder.addValidators(airportValid);
	}
	
	
	// --- MAPPINGS ---
	
	@GetMapping("/api/airport")
	public ResponseEntity<?> apiAirports() {
		log.debug("RestAirportController.apiAirports().....");
		
		List<Airport> airports = airportService.findAll();
		if (airports.isEmpty()) {
			return new ResponseEntity<String>("no airports found", HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<Airport>>(airports, HttpStatus.FOUND);
	}
	
	@GetMapping("/api/airport/{id}")
	public ResponseEntity<?> apiGetAirportById(@PathVariable("id") long id) {
		
		if (airportService.existsById(id)) {
			return new ResponseEntity<Airport>(airportService.findById(id), HttpStatus.OK);
		}
		return new ResponseEntity<String>("airport with id: " + id + " not found", HttpStatus.NOT_FOUND); 
	}
	
	@PostMapping(value = "/api/airport", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> apiSaveAirport(@Valid @RequestBody Airport airport, BindingResult br) {
		
		if (!br.hasErrors()) {
			if (!airportService.existsById(airport.getAirportId())) {
				
				return new ResponseEntity<Airport>(airportService.save(airport), HttpStatus.CREATED);
			}
			return new ResponseEntity<String>("airport id: " + airport.getAirportId() + " already exists", HttpStatus.CONFLICT);
		}
		return new ResponseEntity<String>(airUtil.getValidationErrors(br), HttpStatus.NOT_ACCEPTABLE); 
	}
	
	@PutMapping(value = "/api/airport", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> apiUpdateAirport(@Valid @RequestBody Airport airport, BindingResult br) {
		
		if (airportService.existsById(airport.getAirportId())) {
			if (!br.hasErrors()) {
				
				Airport airDb = airportService.findById(airport.getAirportId());
				airDb.setAirportName(airport.getAirportName());
				airDb.setAirportCode(airport.getAirportCode());
				airDb.setAirportCity(airport.getAirportCity());
				airDb.setAirportState(airport.getAirportState());
				
				return new ResponseEntity<Airport>(airportService.save(airDb), HttpStatus.OK); 
				
			}
			return new ResponseEntity<String>(airUtil.getValidationErrors(br), HttpStatus.NOT_ACCEPTABLE); 
		}
		return new ResponseEntity<String>("airport with id: " + airport.getAirportId() + " not found", HttpStatus.NOT_FOUND);  
	}
	
	@DeleteMapping(value = "/api/airport/{id}")
	public ResponseEntity<?> apiDeleteAirport(@PathVariable("id") long id) {
		
		if (airportService.existsById(id)) {
			airportService.deleteById(id);
			return new ResponseEntity<String>("airport with id: " + id + " was deleted", HttpStatus.OK);  
		}
		return new ResponseEntity<String>("airport with id: " + id + " not found", HttpStatus.NOT_FOUND);   
	}
}
