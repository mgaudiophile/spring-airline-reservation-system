package com.synergisticit.service;

import java.util.List;

import com.synergisticit.domain.Airport;

public interface AirportService {

	Airport save(Airport airport);
	
	List<Airport> findAll();
	Airport findById(long id);
	boolean existsById(long id);
	
	void deleteById(long id);
	
	List<Airport> search(String keyword);
}
