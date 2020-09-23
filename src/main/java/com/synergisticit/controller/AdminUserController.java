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
import com.synergisticit.service.UserService;
import com.synergisticit.utilities.AirlineUtilities;
import com.synergisticit.validator.AdminUserValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class AdminUserController {

	private AirlineUtilities airUtil;
	private UserService userService;
	private AdminUserValidator adminUserValid;
	
	public AdminUserController(AirlineUtilities airUtil, 
								UserService userService, 
								AdminUserValidator adminUserValid) {
		
		this.airUtil = airUtil;
		this.userService = userService;
		this.adminUserValid = adminUserValid;
	}

	@InitBinder("user")
	public void initAdminUserValidatorBinder(WebDataBinder binder) {
		binder.addValidators(adminUserValid);
	}
	
	
	// --- MAPPINGS ---
	
	@PostMapping("/adminSaveUser")
	public String adminSaveUser(@Valid @ModelAttribute User user, BindingResult br, Model model) {
		log.debug("AdminUserController.adminSaveUser().....");
		
		if (!br.hasErrors()) {
			userService.save(user);
			model.addAttribute("user", new User());
		} else {
			model.addAttribute("user", user);
		}
		
		airUtil.buildModel(model);
		return "admin";
	}
	
	@RequestMapping("/adminUpdateUser")
	public String adminUpdateUser(User user, @RequestParam long userId, Model model) {
		log.debug("AdminUserController.adminUpdateUser().....");
		
		if (userService.existsById(userId)) {
			user = userService.findById(userId);
			model.addAttribute("selectedRoles", user.getRoles());
			model.addAttribute("user", user);
		} else {
			model.addAttribute("user", new User());
		}
		
		airUtil.buildModel(model);
		return "admin";
	}
	
	@RequestMapping("/adminDeleteUser")
	public String adminDeleteUser(User user, @RequestParam long userId, Model model) {
		log.debug("AdminUserController.adminDeleteUser().....");
		
		userService.deleteById(userId);
		model.addAttribute("user", new User());
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
