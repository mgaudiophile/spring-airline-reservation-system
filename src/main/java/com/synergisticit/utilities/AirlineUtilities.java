package com.synergisticit.utilities;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.synergisticit.domain.Address;
import com.synergisticit.domain.Airport;
import com.synergisticit.domain.Customer;
import com.synergisticit.domain.Register;
import com.synergisticit.domain.User;
import com.synergisticit.service.AirportService;
import com.synergisticit.service.CustomerService;
import com.synergisticit.service.FlightService;
import com.synergisticit.service.RoleService;
import com.synergisticit.service.UserService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
public class AirlineUtilities {

	private RoleService roleService;
	private UserService userService;
	private FlightService flightService;
	private AirportService airportService;
	private CustomerService customerService;
	
	@Autowired
	public AirlineUtilities(RoleService roleService, 
							UserService userService, 
							FlightService flightService, 
							AirportService airportService,
							CustomerService customerService) {
		
		this.roleService = roleService;
		this.userService = userService;
		this.flightService = flightService;
		this.airportService = airportService;
		this.customerService = customerService;
	}
	
	public void buildModel(Model model) {
		model.addAttribute("roles", roleService.findAll());
		model.addAttribute("users", userService.findAll());
		model.addAttribute("flights", flightService.findAll());
		model.addAttribute("airports", airportService.findAll());
	}
	
	public List<String> getSuggestions(List<Airport> airports) {
		
		List<String> codes = new ArrayList<>();
		for (Airport a : airports) {
			codes.add(a.getAirportCode());
		}
		return codes;
	}
	
	public void registerNewUser(Register register) {
		log.debug("AirlineUtilities.registerNewUser().....");
		
		// --- saving user ---
		User user = new User();
		user.setUsername(register.getUsername());
		user.setPassword(register.getPassword());
		user.setEmail(register.getEmail());
		user.setRoles(roleService.findAll().subList(1, 2));
		userService.save(user);
		
		// --- saving customer ---
		Customer customer = new Customer();
		customer.setName(register.getName());
		
		Address addr = new Address();
		addr.setAddressLine1(register.getAddressLine1());
		addr.setAddressLine2(register.getAddressLine2());
		addr.setCity(register.getCity());
		addr.setState(register.getState());
		addr.setZip(register.getZip());
		
		customer.setCustomerAddress(addr);
		customer.setPhone(register.getMobile());
		customer.setEmail(register.getEmail());
		
		customer.setDob(register.getDob());
		customer.setSsn(register.getSsn());
		customer.setGender(register.getGender());
		
		customer.setUser(user);
		
		customerService.save(customer);
	}
}
