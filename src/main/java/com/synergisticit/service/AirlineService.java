package com.synergisticit.service;

import java.util.List;

import com.synergisticit.domain.Airline;

public interface AirlineService {

	Airline save(Airline airline);
	
	List<Airline> findAll();
	Airline findById(long id);
	boolean existsById(long id);
	
	void deleteById(long id);
}
