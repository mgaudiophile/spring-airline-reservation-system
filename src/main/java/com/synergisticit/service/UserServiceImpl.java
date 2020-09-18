package com.synergisticit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergisticit.domain.User;
import com.synergisticit.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepo;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepo) {
		this.userRepo = userRepo;
	}
	
	@Override
	public User save(User user) {
		
		return userRepo.save(user);
	}

	@Override
	public List<User> findAll() {
		
		return userRepo.findAll();
	}

	@Override
	public User findById(long id) {
		
		Optional<User> opt = userRepo.findById(id);
		
		return opt.isPresent() ? opt.get() : null;
	}

	@Override
	public User updateById(long id) {
		
		return findById(id);
	}

	@Override
	public void deleteById(long id) {
		
		userRepo.deleteById(id);
	}

}
