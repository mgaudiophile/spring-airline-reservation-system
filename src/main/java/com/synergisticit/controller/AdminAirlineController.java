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
import com.synergisticit.service.AirlineService;
import com.synergisticit.utilities.AirlineUtilities;
import com.synergisticit.validator.AdminAirlineValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class AdminAirlineController {

	private AirlineUtilities airUtil;
	private AirlineService airlineService;
	private AdminAirlineValidator airlineValid;
	
	public AdminAirlineController(AirlineUtilities airUtil, 
									AirlineService airlineService,
									AdminAirlineValidator airlineValid) {
		this.airUtil = airUtil;
		this.airlineService = airlineService;
		this.airlineValid = airlineValid;
	}
	
	@InitBinder("airline")
	public void initAdminAirlineValidatorBinder(WebDataBinder binder) {
		binder.addValidators(airlineValid);
	}
	
	
	// --- MAPPINGS ---
	
	@PostMapping("/adminSaveAirline")
	public String adminSaveAirline(@Valid @ModelAttribute Airline airline, BindingResult br, Model model) {
		log.debug("AdminAirlineController.adminSaveAirline().....");
		
		if (!br.hasErrors()) {
			airlineService.save(airline);
			model.addAttribute("airline", new Airline());
		}
		
		airUtil.buildModel(model);
		return "admin";
	}
	
	@RequestMapping("/adminUpdateAirline")
	public String adminUpdateAirline(Airline airline, @RequestParam long airlineId, Model model) {
		log.debug("AdminAirlineController.adminUpdateAirline().....");
		
		if (airlineService.existsById(airlineId)) {
			model.addAttribute("airline", airlineService.findById(airlineId));
		} else {
			model.addAttribute("airline", new Airline());
		}
		
		airUtil.buildModel(model);
		return "admin";
	}
	
	@RequestMapping("/adminDeleteAirline")
	public String adminDeleteAirline(Airline airline, @RequestParam long airlineId, Model model) {
		log.debug("AdminAirlineController.adminDeleteAirline().....");
		
		airlineService.deleteById(airlineId);
		model.addAttribute("airline", new Airline());
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
