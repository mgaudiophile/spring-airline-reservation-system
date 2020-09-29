package com.synergisticit.utilities;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.synergisticit.domain.Airline;
import com.synergisticit.domain.Airport;
import com.synergisticit.domain.Customer;
import com.synergisticit.domain.Flight;
import com.synergisticit.domain.Role;
import com.synergisticit.domain.User;
import com.synergisticit.service.AirlineService;
import com.synergisticit.service.AirportService;
import com.synergisticit.service.CustomerService;
import com.synergisticit.service.FlightService;
import com.synergisticit.service.RoleService;
import com.synergisticit.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

	private AirlineUtilities airUtil;
	private AirportService airportService;
	private AirlineService airlineService;
	private FlightService flightService;
	private UserService userService;
	private RoleService roleService;
	private CustomerService custService;
	private ResourceLoader resourceLoader;
	private LocalDateTimeDeserializer localDateTimeDeserializer;
	
	public CommandLineRunnerImpl(AirlineUtilities airUtil,
									AirportService airportService,
									AirlineService airlineService,
									FlightService flightService,
									ResourceLoader resourceLoader,
									UserService userService,
									RoleService roleService,
									CustomerService custService,
									LocalDateTimeDeserializer localDateTimeDeserializer) {
		
		this.airUtil = airUtil;
		this.airportService = airportService;
		this.airlineService = airlineService;
		this.flightService = flightService;
		this.resourceLoader = resourceLoader;
		this.userService = userService;
		this.roleService = roleService;
		this.custService = custService;
		this.localDateTimeDeserializer = localDateTimeDeserializer;
	}
	
	@Override
	public void run(String... args) throws Exception {
	
		loadDefaultData("classpath:static/json/roles.json", "roles");
		loadDefaultData("classpath:static/json/users.json", "users");
		loadDefaultData("classpath:static/json/customers.json", "customers");
		loadDefaultData("classpath:static/json/airlines.json", "airlines");
		loadDefaultData("classpath:static/json/airports.json", "airports");
		loadDefaultData("classpath:static/json/flights.json", "flights");
		
	}

	private void loadDefaultData(String path, String entity) throws Exception {
		
		Resource resource = resourceLoader.getResource(path);
	    InputStream inputStream = resource.getInputStream();
	    
	    try {
	    	byte[] bdata = FileCopyUtils.copyToByteArray(inputStream);
            String data = new String(bdata, StandardCharsets.UTF_8);
	    	
            GsonBuilder gsonBuilder = null;
            Gson gson = null;
            Type listType = null;
            
            switch(entity) {
            
            case "roles":
            	gson = new Gson();
            	listType = new TypeToken<List<Role>>() {}.getType();
            	List<Role> roles = gson.fromJson(data, listType);
            	
            	log.debug("uploading roles.json to database..............");
                for (Role r : roles)
                	roleService.save(r);
            	break;
            	
            case "users":
            	gson = new Gson();
            	listType = new TypeToken<List<User>>() {}.getType();
            	List<User> users = gson.fromJson(data, listType);
            	
            	log.debug("uploading users.json to database..............");
                for (User u : users)
                	userService.save(u);
            	break;
            	
            case "customers":
            	gson = new Gson();
            	listType = new TypeToken<List<Customer>>() {}.getType();
            	List<Customer> customers = gson.fromJson(data, listType);
            	
            	log.debug("uploading customers.json to database..............");
                for (Customer c : customers)
                	custService.save(c);
            	break;
            	
            case "airlines":
            	gson = new Gson();
            	listType = new TypeToken<List<Airline>>() {}.getType();
            	List<Airline> airlines = gson.fromJson(data, listType);
            	
            	log.debug("uploading airlines.json to database..............");
                for (Airline a : airlines)
                	airlineService.save(a);
            	break;
            case "airports":
            	gson = new Gson();
            	listType = new TypeToken<List<Airport>>() {}.getType();
            	List<Airport> airports = gson.fromJson(data, listType);
            	
            	log.debug("uploading airports.json to database..............");
                for (Airport a : airports)
                	airportService.save(a);
            	break;
            case "flights":
            	gsonBuilder = new GsonBuilder();
                gsonBuilder.registerTypeAdapter(LocalDateTime.class, localDateTimeDeserializer);
                gson = gsonBuilder.setPrettyPrinting().create();
                listType = new TypeToken<List<Flight>>() {}.getType();
                List<Flight> flights = gson.fromJson(data, listType);

                log.debug("uploading flights.json to database..............");
                for (Flight f : flights) {
                	airUtil.prettifyDateTimeETA(f);
        			flightService.save(f);
                }
            	break;
            	
            	default:
            		log.debug("unrecognized entity for default loading");
            }
	    	
	    } catch (IOException e) {
	    	log.error("IOException", e);
	    }
	}
}
