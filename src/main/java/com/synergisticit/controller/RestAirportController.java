package com.synergisticit.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.synergisticit.domain.Airport;
import com.synergisticit.service.AirportService;
import com.synergisticit.utilities.AirlineUtilities;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class RestAirportController {

	private AirlineUtilities airUtil;
	private AirportService airportService;
	
	public RestAirportController(AirlineUtilities airUtil, 
									AirportService airportService) {
		this.airUtil = airUtil;
		this.airportService = airportService;
	}
	
	@GetMapping("/api/airports")
	public ResponseEntity<?> apiAirports() {
		log.debug("RestAirportController.apiAirports().....");
		
		List<Airport> airports = airportService.findAll();
		if (airports.isEmpty()) {
			return new ResponseEntity<String>("no airports found", HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<List<Airport>>(airports, HttpStatus.OK);
	}
	
	@GetMapping("/api/airports/search")
	public List<String> apiAirportSearch(HttpServletRequest request) {
		List<Airport> airports = airportService.search(request.getParameter("term"));
		
		return airUtil.getSuggestions(airports);
	}
}
