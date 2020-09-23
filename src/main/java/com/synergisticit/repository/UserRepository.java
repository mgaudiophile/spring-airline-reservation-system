package com.synergisticit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.synergisticit.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);
	
	boolean existsByUsername(String username);
	
	@Query(value = "SELECT * FROM users u WHERE u.userId = :userId AND u.username = :username", nativeQuery=true)
	User findByIdAndUsername(@Param("userId") Long userId, @Param("username") String username);
}
