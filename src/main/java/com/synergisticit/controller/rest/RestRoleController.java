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

import com.synergisticit.domain.Role;
import com.synergisticit.service.RoleService;
import com.synergisticit.utilities.AirlineUtilities;
import com.synergisticit.validator.AdminRoleValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class RestRoleController {

	private AirlineUtilities airUtil;
	private RoleService roleService;
	private AdminRoleValidator roleValid;
	
	public RestRoleController(AirlineUtilities airUtil, 
									RoleService roleService, 
									AdminRoleValidator roleValid) {
		this.airUtil = airUtil;
		this.roleService = roleService;
		this.roleValid = roleValid;
	}
	
	@InitBinder
	public void initRestRoleValidator(WebDataBinder binder) {
		binder.addValidators(roleValid);
	}
	
	
	// --- MAPPINGS ---
	
	@GetMapping("/api/role")
	public ResponseEntity<?> apiRole() {
		log.debug("RestRoleController.apiRole().....");
		
		List<Role> roles = roleService.findAll();
		if (roles.isEmpty()) {
			return new ResponseEntity<String>("no roles found", HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<Role>>(roles, HttpStatus.FOUND);
	}
	
	@GetMapping("/api/role/{id}")
	public ResponseEntity<?> apiGetRoleById(@PathVariable("id") long id) {
		log.debug("RestRoleController.apiGetRoleById().....");
		
		if (roleService.existsById(id)) {
			return new ResponseEntity<Role>(roleService.findById(id), HttpStatus.OK);
		}
		return new ResponseEntity<String>("role with id: " + id + " not found", HttpStatus.NOT_FOUND); 
	}
	
	@PostMapping(value = "/api/role", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> apiSaveRole(@Valid @RequestBody Role role, BindingResult br) {
		log.debug("RestRoleController.apiSaveRole().....");
		
		if (!br.hasErrors()) {
			if (!roleService.existsById(role.getRoleId())) {
				
				return new ResponseEntity<Role>(roleService.save(role), HttpStatus.CREATED);
			}
			return new ResponseEntity<String>("role id: " + role.getRoleId() + " already exists", HttpStatus.CONFLICT);
		}
		return new ResponseEntity<String>(airUtil.getValidationErrors(br), HttpStatus.NOT_ACCEPTABLE); 
	}
	
	@PutMapping(value = "/api/role", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> apiUpdateRole(@Valid @RequestBody Role role, BindingResult br) {
		log.debug("RestRoleController.apiUpdateRole().....");
		
		if (roleService.existsById(role.getRoleId())) {
			if (!br.hasErrors()) {
				
				Role rDb = roleService.findById(role.getRoleId());
				rDb.setRoleName(role.getRoleName());
					
				return new ResponseEntity<Role>(roleService.save(rDb), HttpStatus.OK); 
				
			}
			return new ResponseEntity<String>(airUtil.getValidationErrors(br), HttpStatus.NOT_ACCEPTABLE); 
		}
		return new ResponseEntity<String>("role with id: " + role.getRoleId() + " not found", HttpStatus.NOT_FOUND);  
	}
	
	@DeleteMapping(value = "/api/role/{id}")
	public ResponseEntity<?> apiDeleteRole(@PathVariable("id") long id) {
		log.debug("RestRoleController.apiDeleteRole().....");
		
		if (roleService.existsById(id)) {
			roleService.deleteById(id);
			return new ResponseEntity<String>("role with id: " + id + " was deleted", HttpStatus.OK);  
		}
		return new ResponseEntity<String>("role with id: " + id + " not found", HttpStatus.NOT_FOUND);   
	}
}
