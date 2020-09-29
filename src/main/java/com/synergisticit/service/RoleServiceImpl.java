package com.synergisticit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergisticit.domain.Role;
import com.synergisticit.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepo;
	
	@Override
	public Role save(Role role) {
		
		return roleRepo.save(role);
	}
	
	@Override
	public List<Role> findAll() { 
		
		return roleRepo.findAll();
	}

	@Override
	public Role findById(long id) {
		Optional<Role> opt = roleRepo.findById(id);
		return opt.isPresent() ? opt.get() : null;
	}

	@Override
	public void deleteById(long id) {
		
		roleRepo.deleteById(id);
	}
}
