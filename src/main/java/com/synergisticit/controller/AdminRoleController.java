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

import com.synergisticit.domain.Airline;
import com.synergisticit.domain.Airport;
import com.synergisticit.domain.Customer;
import com.synergisticit.domain.Flight;
import com.synergisticit.domain.Role;
import com.synergisticit.domain.User;
import com.synergisticit.service.RoleService;
import com.synergisticit.utilities.AirlineUtilities;
import com.synergisticit.validator.AdminRoleValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class AdminRoleController {

	private AirlineUtilities airUtil;
	private RoleService roleService;
	private AdminRoleValidator roleValid;

	public AdminRoleController(AirlineUtilities airUtil, 
								RoleService roleService,
								AdminRoleValidator roleValid) {
		this.airUtil = airUtil;
		this.roleService = roleService;
		this.roleValid = roleValid;
	}
	
	@InitBinder("role")
	public void initAdminRoleValidator(WebDataBinder binder) {
		binder.addValidators(roleValid);
	}
	
	

	// --- MAPPINGS ---

	@PostMapping("/adminSaveRole")
	public String adminSaveRole(@Valid @ModelAttribute Role role, BindingResult br, Model model) {
		log.debug("AdminRoleController.adminSaveRole().....");

		if (!br.hasErrors()) {
			roleService.save(role);
			model.addAttribute("role", new Role());
		}

		airUtil.buildModel(model);
		return "admin";
	}

	@RequestMapping("/adminUpdateRole")
	public String adminUpdateRole(Role role, @RequestParam long roleId, Model model) {
		log.debug("AdminRoleController.adminUpdateRole().....");

		if (roleService.existsById(roleId)) {
			model.addAttribute("role", roleService.findById(roleId));
		} else {
			model.addAttribute("role", new Role());
		}

		airUtil.buildModel(model);
		return "admin";
	}

	@RequestMapping("/adminDeleteRole")
	public String adminDeleteRole(Role role, @RequestParam long roleId, Model model) {
		log.debug("AdminRoleController.adminDeleteRole().....");

		roleService.deleteById(roleId);
		model.addAttribute("role", new Role());
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
