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

import com.synergisticit.domain.Airline;
import com.synergisticit.domain.Airport;
import com.synergisticit.domain.Customer;
import com.synergisticit.domain.Flight;
import com.synergisticit.domain.User;
import com.synergisticit.service.FlightService;
import com.synergisticit.utilities.AirlineUtilities;
import com.synergisticit.validator.AdminFlightValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class AdminFlightController {

	private AirlineUtilities airUtil;
	private FlightService flightService;
	private AdminFlightValidator flightValid;
	
	public AdminFlightController(AirlineUtilities airUtil, 
									FlightService flightService,
									AdminFlightValidator flightValid) {
		this.airUtil = airUtil;
		this.flightService = flightService;
		this.flightValid = flightValid;
	}
	
	@InitBinder("flight")
	public void initAdminFlightValidatorBinder(WebDataBinder binder) {
		binder.addValidators(flightValid);
	}
	
	
	// --- MAPPINGS ---
	
	@PostMapping("/adminSaveFlight")
	public String adminSaveFlight(@Valid @ModelAttribute Flight flight, BindingResult br, Model model) {
		log.debug("AdminFlightController.adminSaveFlight().....");
		
		if (!br.hasErrors()) {
			airUtil.prettifyDateTimeETA(flight);
			flightService.save(flight);
			model.addAttribute("flight", new Flight());
		}
		
		airUtil.buildModel(model);
		return "admin";
	}
	
	@RequestMapping("/adminUpdateFlight")
	public String adminUpdateFlight(Flight flight, @RequestParam long flightId, Model model) {
		log.debug("AdminFlightController.adminUpdateFlight().....");
		
		if (flightService.existsById(flightId)) {
			model.addAttribute("flight", flightService.findById(flightId));
		} else {
			model.addAttribute("flight", new Flight());
		}
		
		airUtil.buildModel(model);
		return "admin";
	}
	
	@RequestMapping("/adminDeleteFlight")
	public String adminDeleteFlight(Flight flight, @RequestParam long flightId, Model model) {
		log.debug("AdminFlightController.adminDeleteFlight().....");
		
		flightService.deleteById(flightId);
		model.addAttribute("flight", new Flight());
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
	
	@ModelAttribute
	public Airline airline() {
		return new Airline();
	}
	
	@ModelAttribute
	public Customer customer() {
		return new Customer();
	}
}
