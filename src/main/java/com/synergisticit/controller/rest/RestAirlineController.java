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
import com.synergisticit.service.AirlineService;
import com.synergisticit.utilities.AirlineUtilities;
import com.synergisticit.validator.AdminAirlineValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class RestAirlineController {

	private AirlineUtilities airUtil;
	private AirlineService airlineService;
	private AdminAirlineValidator airlineValid;
	
	public RestAirlineController(AirlineUtilities airUtil, 
									AirlineService airlineService, 
									AdminAirlineValidator airlineValid) {
		this.airUtil = airUtil;
		this.airlineService = airlineService;
		this.airlineValid = airlineValid;
	}
	
	@InitBinder
	public void initRestAirlineValidator(WebDataBinder binder) {
		binder.addValidators(airlineValid);
	}
	
	
	// --- MAPPINGS ---
	
	@GetMapping("/api/airline")
	public ResponseEntity<?> apiAirline() {
		log.debug("RestAirlineController.apiAirline().....");
		
		List<Airline> airlines = airlineService.findAll();
		if (airlines.isEmpty()) {
			return new ResponseEntity<String>("no airlines found", HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<Airline>>(airlines, HttpStatus.FOUND);
	}
	
	@GetMapping("/api/airline/{id}")
	public ResponseEntity<?> apiGetAirlineById(@PathVariable("id") long id) {
		
		if (airlineService.existsById(id)) {
			return new ResponseEntity<Airline>(airlineService.findById(id), HttpStatus.OK);
		}
		return new ResponseEntity<String>("airline with id: " + id + " not found", HttpStatus.NOT_FOUND); 
	}
	
	@PostMapping(value = "/api/airline", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> apiSaveAirline(@Valid @RequestBody Airline airline, BindingResult br) {
		
		if (!br.hasErrors()) {
			if (!airlineService.existsById(airline.getAirlineId())) {
				
				return new ResponseEntity<Airline>(airlineService.save(airline), HttpStatus.CREATED);
			}
			return new ResponseEntity<String>("airline id: " + airline.getAirlineId() + " already exists", HttpStatus.CONFLICT);
		}
		return new ResponseEntity<String>(airUtil.getValidationErrors(br), HttpStatus.NOT_ACCEPTABLE); 
	}
	
	@PutMapping(value = "/api/airline", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> apiUpdateAirline(@Valid @RequestBody Airline airline, BindingResult br) {
		
		if (airlineService.existsById(airline.getAirlineId())) {
			if (!br.hasErrors()) {
				
				Airline aDb = airlineService.findById(airline.getAirlineId());
				aDb.setAirlineName(airline.getAirlineName());
				aDb.setAirlineCode(airline.getAirlineCode());
				
				return new ResponseEntity<Airline>(airlineService.save(aDb), HttpStatus.OK); 
				
			}
			return new ResponseEntity<String>(airUtil.getValidationErrors(br), HttpStatus.NOT_ACCEPTABLE); 
		}
		return new ResponseEntity<String>("airline with id: " + airline.getAirlineId() + " not found", HttpStatus.NOT_FOUND);  
	}
	
	@DeleteMapping(value = "/api/airline/{id}")
	public ResponseEntity<?> apiDeleteAirline(@PathVariable("id") long id) {
		
		if (airlineService.existsById(id)) {
			airlineService.deleteById(id);
			return new ResponseEntity<String>("airline with id: " + id + " was deleted", HttpStatus.OK);  
		}
		return new ResponseEntity<String>("airline with id: " + id + " not found", HttpStatus.NOT_FOUND);   
	}
}
