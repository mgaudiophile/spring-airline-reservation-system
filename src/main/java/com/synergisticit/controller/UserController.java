package com.synergisticit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.synergisticit.domain.User;
import com.synergisticit.service.RoleService;
import com.synergisticit.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class UserController {
	
	private RoleService roleService;
	private UserService userService;
	
	@Autowired
	public UserController(RoleService roleService, UserService userService) {
		
		this.roleService = roleService;
		this.userService = userService;
	}
	
	@RequestMapping("/userForm")
	public String userForm(Model model) {
		log.debug("UserController.userForm().....");
		
		model.addAttribute("roles", roleService.findAll());
		model.addAttribute("users", userService.findAll());
		
		return "user";
	}
	
	@PostMapping("/saveUser")
	public String saveUser(User user, Model model) {
		log.debug("UserController.saveUser().....");
		
		userService.save(user);
		
		model.addAttribute("roles", roleService.findAll());
		model.addAttribute("users", userService.findAll());
		model.addAttribute("user", new User());
		
		return "user";
	}
	
	@ModelAttribute
	public User user() {
		return new User();
	}
}
