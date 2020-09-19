package com.synergisticit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.synergisticit.domain.User;
import com.synergisticit.service.UserService;
import com.synergisticit.utilities.AirlineUtilities;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class UserController {
	
	private AirlineUtilities airUtil;
	private UserService userService;
	
	@Autowired
	public UserController(AirlineUtilities airUtil, UserService userService) {
		this.airUtil = airUtil;
		this.userService = userService;
	}
	
	@GetMapping("/userForm")
	public String userForm(Model model) {
		log.debug("UserController.userForm().....");
		
		airUtil.buildModel(model);
		return "user";
	}
	
	@PostMapping("/saveUser")
	public String saveUser(User user, Model model) {
		log.debug("UserController.saveUser().....");
		
		userService.save(user);
		model.addAttribute("user", new User());
		
		airUtil.buildModel(model);
		return "user";
	}
	
	@GetMapping("/updateUser")
	public String updateUser(User user, @RequestParam Long userId, Model model) {
		log.debug("UserController.updateUser().....");
		
		if (userService.existsById(userId)) {
			user = userService.findById(userId);
			model.addAttribute("user", user);
			model.addAttribute("selectedRoles", user.getRoles());
		} else {
			model.addAttribute("user", new User());
		}
		
		airUtil.buildModel(model);
		return "user";
	}
	
	@GetMapping("/deleteUser")
	public String deleteUser(User user, @RequestParam Long userId, Model model) {
		log.debug("UserController.deleteUser().....");
		
		if (userService.existsById(userId)) {
			userService.deleteById(userId);
		}
		
		airUtil.buildModel(model);
		return "user";
	}
	
	@ModelAttribute
	public User user() {
		return new User();
	}
}
