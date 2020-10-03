package com.synergisticit.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.synergisticit.domain.Role;
import com.synergisticit.domain.User;
import com.synergisticit.service.CustomerService;
import com.synergisticit.utilities.AirlineUtilities;
import com.synergisticit.validator.AdminCustomerValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class AdminCustomerController {

	private AirlineUtilities airUtil;
	private CustomerService custService;
	private AdminCustomerValidator custValid;
	
	@Autowired
	public AdminCustomerController(AirlineUtilities airUtil, 
									CustomerService custService, 
									AdminCustomerValidator custValid) {
		this.airUtil = airUtil;
		this.custService = custService;
		this.custValid = custValid;
	}
	
	@InitBinder("customer")
	public void initAdminCustomerValidator(WebDataBinder binder) {
		binder.addValidators(custValid);
	}
	
	
	// --- MAPPINGS ---
	
	@PostMapping("/adminSaveCustomer")
	public String adminSaveCustomer(@Valid @ModelAttribute Customer customer, BindingResult br, Model model) {
		log.debug("AdminCustomerController.adminSaveCustomer().....");
		
		if (!br.hasErrors()) {
			custService.save(customer);
			model.addAttribute("customer", new Customer());
		} 
		
		airUtil.buildModel(model);
		return "admin";
	}
	
	@RequestMapping("/adminUpdateCustomer")
	public String adminUpdateCustomer(Customer customer, @RequestParam long customerId, Model model) {
	
		if (custService.existsById(customerId)) {
			model.addAttribute("customer", custService.findById(customerId));
		} else {
			model.addAttribute("customer", new Customer());
		}
		
		airUtil.buildModel(model);
		return "admin";
	}
	
	@RequestMapping("/adminDeleteCustomer")
	public String adminDeleteCustomer(Customer customer, @RequestParam long customerId, Model model) {
		
		custService.deleteById(customerId);
		model.addAttribute("customer", new Customer());
		airUtil.buildModel(model);
		
		return "admin";
	}
	
	
	// --- MODEL ATTRIBUTES ---
	@ModelAttribute
	public Role role() {
		return new Role();
	}
	
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
