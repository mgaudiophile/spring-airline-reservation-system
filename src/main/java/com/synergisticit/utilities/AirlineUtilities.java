package com.synergisticit.utilities;

import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.synergisticit.domain.Address;
import com.synergisticit.domain.Airport;
import com.synergisticit.domain.Customer;
import com.synergisticit.domain.Flight;
import com.synergisticit.domain.Passenger;
import com.synergisticit.domain.Payment;
import com.synergisticit.domain.PrettyFlight;
import com.synergisticit.domain.Register;
import com.synergisticit.domain.Search;
import com.synergisticit.domain.Ticket;
import com.synergisticit.domain.User;
import com.synergisticit.service.AirlineService;
import com.synergisticit.service.AirportService;
import com.synergisticit.service.CustomerService;
import com.synergisticit.service.FlightService;
import com.synergisticit.service.RoleService;
import com.synergisticit.service.TicketService;
import com.synergisticit.service.UserService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
public class AirlineUtilities {

	private RoleService roleService;
	private UserService userService;
	private FlightService flightService;
	private AirportService airportService;
	private AirlineService airlineService;
	private CustomerService customerService;
	private TicketService ticketService;
	
	private List<Passenger> passengers = new ArrayList<>();
	
	@Autowired
	public AirlineUtilities(RoleService roleService, 
							UserService userService, 
							FlightService flightService, 
							AirportService airportService,
							AirlineService airlineService,
							CustomerService customerService,
							TicketService ticketService) {
		
		this.roleService = roleService;
		this.userService = userService;
		this.flightService = flightService;
		this.airportService = airportService;
		this.airlineService = airlineService;
		this.customerService = customerService;
		this.ticketService = ticketService;
	}
	
	public void buildModel(Model model) {
		model.addAttribute("roles", roleService.findAll());
		model.addAttribute("users", userService.findAll());
		model.addAttribute("flights", flightService.findAll());
		model.addAttribute("airports", airportService.findAll());
		model.addAttribute("airlines", airlineService.findAll());
		model.addAttribute("customers", customerService.findAll());
		model.addAttribute("listOfTickets", ticketService.findAll());
	}
	
	public void buildCustomerModel(Model model) {
		model.addAttribute("airportCodes", buildAirportMap(airportService.findAllAirportCode(), airportService.findAllAirportName()));
		List<Ticket> tickets = ticketService.findAllByCustomerId(getCurrentCustomer().getCustomerId());
		
		model.addAttribute("listOfTickets", ticketService.findAllByCustomerId(getCurrentCustomer().getCustomerId()));
	}
	
	public void initBookingModel(long flightId, long tickets, long total, Model model) {
		model.addAttribute("flightId", flightId);
		model.addAttribute("tickets", tickets);
		model.addAttribute("total", total);
	}
	
	public void updateBookingModel(Passenger passenger, Model model) {
		passenger.setTicketNumber(passenger.getTicketNumber()-1L);
		model.addAttribute("tickets", passenger.getTicketNumber());
		model.addAttribute("passengerSaved", "Passenger "+getPassengersSize()+" has been saved.");
	}
	
	public User getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		return userService.findByUsername(currentPrincipalName);
	}
	
	public Customer getCurrentCustomer() {
		return customerService.findByUserId(getCurrentUser().getUserId());
	}
	
	public void addPassenger(Passenger passenger) {
		passengers.add(passenger);
	}
	
	public void resetPassengers() {
		passengers.clear();
	}
	
	public int getPassengersSize() {
		return passengers.size();
	}
	
	public Airport findByCode(String code) {
		return airportService.findByAirportCode(code);
	}
	
	public void purchaseTicket(Payment payment, Long flightId) {
		Ticket t = new Ticket();
		t.setCustomer(getCurrentCustomer());
		
		
		t.setFlight(flightService.findById(flightId));
		t.setTotal(payment.getTotal());
		t.setPassengers(passengers);
		
		ticketService.save(t);
		
		resetPassengers();
	}
	
	public List<Flight> searchFlights(Search search) {
		log.debug("AirlineUtilities.searchFlights().....");
		Airport from = findByCode(search.getFromInput());
		Airport to = findByCode(search.getToInput());
	
		if (from == null || to == null)
			return new ArrayList<Flight>();
		
		List<Flight> departures = flightService.findByDepartureCode(from.getAirportId(), to.getAirportId());
		for (Iterator<Flight> itr = departures.listIterator(); itr.hasNext(); ) {
		    Flight f = itr.next();
			if (!(f.getDepartureDateTime().getYear() == search.getDepart().getYear() && 
					f.getDepartureDateTime().getMonth() == search.getDepart().getMonth() && 
					f.getDepartureDateTime().getDayOfMonth() == search.getDepart().getDayOfMonth())) {
				
		        itr.remove();
		    }
		}
		
		return departures;
	}
	
	public void prettifyDateTimeETA(Flight flight) {
		double minutes = flight.getDepartureDateTime().until(flight.getArrivalDateTime(), ChronoUnit.MINUTES)/60.0;
		String eta = String.format("%.2f", minutes);
		
		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm a");
		
		String depart = flight.getDepartureDateTime().format(timeFormatter);
		String arrive = flight.getArrivalDateTime().format(timeFormatter);
		
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("E, MMM d");
		
		String date = flight.getDepartureDateTime().format(dateFormatter);
		
		PrettyFlight pf = new PrettyFlight();
		pf.setEta(eta);
		pf.setDepart(depart);
		pf.setArrive(arrive);
		pf.setDate(date);
		
		flight.setPrettyFlight(pf);
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
	
	private Map<String, String> buildAirportMap(List<String> codes, List<String> names) {
		Map<String, String> map = new HashMap<>();
		for (int i = 0; i < codes.size(); i++) {
			map.put(codes.get(i), names.get(i) + " -- (" + codes.get(i) + ")");
		}
		return map;
	}
}
