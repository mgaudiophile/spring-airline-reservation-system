package com.synergisticit.service;

import java.util.List;

import com.synergisticit.domain.User;

public interface UserService {

	User save(User user);
	
	List<User> findAll();
	User findById(long id);
	
	User updateById(long id);
	
	void deleteById(long id);
}
