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

import com.synergisticit.domain.Customer;
import com.synergisticit.domain.User;
import com.synergisticit.service.CustomerService;
import com.synergisticit.service.UserService;
import com.synergisticit.utilities.AirlineUtilities;
import com.synergisticit.validator.AdminCustomerValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class RestCustomerController {

	private AirlineUtilities airUtil;
	private CustomerService custService;
	private UserService userService;
	private AdminCustomerValidator custValid;
	
	public RestCustomerController(AirlineUtilities airUtil, 
									CustomerService custService, 
									UserService userService,
									AdminCustomerValidator custValid) {
		this.airUtil = airUtil;
		this.custService = custService;
		this.userService = userService;
		this.custValid = custValid;
	}
	
	@InitBinder
	public void initRestCustomerValidator(WebDataBinder binder) {
		binder.addValidators(custValid);
	}
	
	
	// --- MAPPINGS ---
	
	@GetMapping("/api/customer")
	public ResponseEntity<?> apiCustomer() {
		log.debug("RestCustomerController.apiCustomer().....");
		
		List<Customer> customers = custService.findAll();
		if (customers.isEmpty()) {
			return new ResponseEntity<String>("no customers found", HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<Customer>>(customers, HttpStatus.FOUND);
	}
	
	@GetMapping("/api/customer/{id}")
	public ResponseEntity<?> apiGetCustomerById(@PathVariable("id") long id) {
		
		if (custService.existsById(id)) {
			return new ResponseEntity<Customer>(custService.findById(id), HttpStatus.OK);
		}
		return new ResponseEntity<String>("customer with id: " + id + " not found", HttpStatus.NOT_FOUND); 
	}
	
	@PostMapping(value = "/api/customer", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> apiSaveCustomer(@Valid @RequestBody Customer customer, BindingResult br) {
		
		if (!br.hasErrors()) {
			if (!custService.existsById(customer.getCustomerId())) {
				
				return new ResponseEntity<Customer>(custService.save(customer), HttpStatus.CREATED);
			}
			return new ResponseEntity<String>("customer id: " + customer.getCustomerId() + " already exists", HttpStatus.CONFLICT);
		}
		return new ResponseEntity<String>(airUtil.getValidationErrors(br), HttpStatus.NOT_ACCEPTABLE); 
	}
	
	@PutMapping(value = "/api/customer", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> apiUpdateCustomer(@Valid @RequestBody Customer customer, BindingResult br) {
		
		if (custService.existsById(customer.getCustomerId())) {
			if (!br.hasErrors()) {
				
				Customer cDb = custService.findById(customer.getCustomerId());
				
				cDb.setName(customer.getName());
				cDb.setCustomerAddress(customer.getCustomerAddress());
				cDb.setEmail(customer.getEmail());
				cDb.setPhone(customer.getPhone());
				cDb.setDob(customer.getDob());
				cDb.setGender(customer.getGender());
				cDb.setSsn(customer.getSsn());
				
				User uDb = userService.findById(customer.getUser().getUserId());
				
				cDb.setUser(uDb);
					
				return new ResponseEntity<Customer>(custService.save(cDb), HttpStatus.OK); 
				
			}
			return new ResponseEntity<String>(airUtil.getValidationErrors(br), HttpStatus.NOT_ACCEPTABLE); 
		}
		return new ResponseEntity<String>("customer with id: " + customer.getCustomerId() + " not found", HttpStatus.NOT_FOUND);  
	}
	
	@DeleteMapping(value = "/api/customer/{id}")
	public ResponseEntity<?> apiDeleteCustomer(@PathVariable("id") long id) {
		
		if (custService.existsById(id)) {
			custService.deleteById(id);
			return new ResponseEntity<String>("customer with id: " + id + " was deleted", HttpStatus.OK);  
		}
		return new ResponseEntity<String>("customer with id: " + id + " not found", HttpStatus.NOT_FOUND);   
	}
}
