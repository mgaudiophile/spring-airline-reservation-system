package com.synergisticit.service;

import java.util.List;

import com.synergisticit.domain.Role;

public interface RoleService {

	Role save(Role role);
	
	List<Role> findAll();
	Role findById(long id);
	boolean existsById(long id);
	
	void deleteById(long id);
}
