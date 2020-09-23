package com.synergisticit.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.synergisticit.domain.User;

public interface UserService {

	User save(User user);
	
	List<User> findAll();
	User findById(long id);
	User findByUsername(String username);
	User findByIdAndUsername(@Param("userId") Long userId, @Param("username") String name);
	boolean existsById(Long id);
	boolean existsByUsername(String username);
	
	User updateById(long id);
	
	void deleteById(long id);
}
