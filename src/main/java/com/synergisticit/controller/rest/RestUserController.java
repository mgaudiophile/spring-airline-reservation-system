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

import com.synergisticit.domain.User;
import com.synergisticit.service.UserService;
import com.synergisticit.utilities.AirlineUtilities;
import com.synergisticit.validator.AdminUserValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class RestUserController {

	private AirlineUtilities airUtil;
	private UserService userService;
	private AdminUserValidator userValid;
	
	public RestUserController(AirlineUtilities airUtil, 
								UserService userService, 
								AdminUserValidator userValid) {
		this.airUtil = airUtil;
		this.userService = userService;
		this.userValid = userValid;
	}
	
	@InitBinder
	public void initRestUserValidator(WebDataBinder binder) {
		binder.addValidators(userValid);
	}
	
	
	// --- MAPPINGS ---
	
	@GetMapping("/api/user")
	public ResponseEntity<?> apiUser() {
		log.debug("RestUserController.apiUser().....");
		
		List<User> users = userService.findAll();
		if (users.isEmpty()) {
			return new ResponseEntity<String>("no users found", HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<User>>(users, HttpStatus.FOUND);
	}
	
	@GetMapping("/api/user/{id}")
	public ResponseEntity<?> apiGetUserById(@PathVariable("id") long id) {
		
		if (userService.existsById(id)) {
			return new ResponseEntity<User>(userService.findById(id), HttpStatus.OK);
		}
		return new ResponseEntity<String>("user with id: " + id + " not found", HttpStatus.NOT_FOUND); 
	}
	
	@PostMapping(value = "/api/user", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> apiSaveUser(@Valid @RequestBody User user, BindingResult br) {
		
		if (!br.hasErrors()) {
			if (!userService.existsById(user.getUserId())) {
				
				return new ResponseEntity<User>(userService.save(user), HttpStatus.CREATED);
			}
			return new ResponseEntity<String>("user id: " + user.getUserId() + " already exists", HttpStatus.CONFLICT);
		}
		return new ResponseEntity<String>(airUtil.getValidationErrors(br), HttpStatus.NOT_ACCEPTABLE); 
	}
	
	@PutMapping(value = "/api/user", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> apiUpdateUser(@Valid @RequestBody User user, BindingResult br) {
		
		if (userService.existsById(user.getUserId())) {
			if (!br.hasErrors()) {
				
				User uDb = userService.findById(user.getUserId());
				
				uDb.setUsername(user.getUsername());
				uDb.setPassword(user.getPassword());
				uDb.setEmail(user.getEmail());
				uDb.setRoles(user.getRoles());
				
				return new ResponseEntity<User>(userService.save(uDb), HttpStatus.OK); 
				
			}
			return new ResponseEntity<String>(airUtil.getValidationErrors(br), HttpStatus.NOT_ACCEPTABLE); 
		}
		return new ResponseEntity<String>("user with id: " + user.getUserId() + " not found", HttpStatus.NOT_FOUND);  
	}
	
	@DeleteMapping(value = "/api/user/{id}")
	public ResponseEntity<?> apiDeleteUser(@PathVariable("id") long id) {
		
		if (userService.existsById(id)) {
			userService.deleteById(id);
			return new ResponseEntity<String>("user with id: " + id + " was deleted", HttpStatus.OK);  
		}
		return new ResponseEntity<String>("user with id: " + id + " not found", HttpStatus.NOT_FOUND);   
	}
}
