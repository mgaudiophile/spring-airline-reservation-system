package com.synergisticit.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.synergisticit.domain.Airport;
import com.synergisticit.domain.Flight;
import com.synergisticit.domain.User;
import com.synergisticit.service.AirportService;
import com.synergisticit.utilities.AirlineUtilities;
import com.synergisticit.validator.AdminAirportValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class AdminAirportController {

	private AirlineUtilities airUtil;
	private AirportService airportService;
	private AdminAirportValidator airportValid;
	
	public AdminAirportController(AirlineUtilities airUtil, 
									AirportService airportService,
									AdminAirportValidator airportValid) {
		this.airUtil = airUtil;
		this.airportService = airportService;
		this.airportValid = airportValid;
	}
	
	@InitBinder("airport")
	public void initAdminAirportValidatorBinder(WebDataBinder binder) {
		binder.addValidators(airportValid);
	}
	
	
	// --- MAPPINGS ---
	
	@PostMapping("/adminSaveAirport")
	public String adminSaveAirport(@Valid @ModelAttribute Airport airport, BindingResult br, Model model) {
		log.debug("AdminAirportController.adminSaveAirport().....");
		
		if (!br.hasErrors()) {
			airportService.save(airport);
			model.addAttribute("airport", new Airport());
		}
		
		airUtil.buildModel(model);
		return "admin";
	}
	
	@RequestMapping("/adminUpdateAirport")
	public String adminUpdateAirport(Airport airport, @RequestParam long airportId, Model model) {
		log.debug("AdminAirportController.adminUpdateAirport().....");
		
		if (airportService.existsById(airportId)) {
			airport = airportService.findById(airportId);
			model.addAttribute("airport", airport);
		} else {
			model.addAttribute("airport", new Airport());
		}
		
		airUtil.buildModel(model);
		return "admin";
	}
	
	@RequestMapping("/adminDeleteAirport")
	public String adminDeleteAirport(Airport airport, @RequestParam long airportId, Model model) {
		log.debug("AdminAirportController.adminDeleteAirport().....");
		
		airportService.deleteById(airportId);
		model.addAttribute("airport", new Airport());
		airUtil.buildModel(model);
		
		return "admin";
	}
	
	
	// --- MODEL ATTRIBUTES ---
	@ModelAttribute
	public User user() {
		return new User();
	}
	
	@ModelAttribute
	public Flight flight() {
		return new Flight();
	}
	
	@ModelAttribute
	public Airport airport() {
		return new Airport();
	}
}
