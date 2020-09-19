package com.synergisticit.utilities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.synergisticit.service.RoleService;
import com.synergisticit.service.UserService;


@Component
public class AirlineUtilities {

	private RoleService roleService;
	private UserService userService;
	
	@Autowired
	public AirlineUtilities(RoleService roleService, UserService userService) {
		
		this.roleService = roleService;
		this.userService = userService;
	}
	
	public void buildModel(Model model) {
		model.addAttribute("roles", roleService.findAll());
		model.addAttribute("users", userService.findAll());
	}
}
