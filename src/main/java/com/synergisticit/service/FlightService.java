package com.synergisticit.service;

import java.util.List;

import com.synergisticit.domain.Flight;

public interface FlightService {

	Flight save(Flight flight);
	
	List<Flight> findAll();
	Flight findById(long id);
	boolean existsById(long id);
	
	void deleteById(long id);
}
