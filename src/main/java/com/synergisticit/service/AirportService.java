package com.synergisticit.service;

import java.util.List;

import com.synergisticit.domain.Airport;

public interface AirportService {

	Airport save(Airport airport);
	
	List<Airport> findAll();
	Airport findById(long id);
	boolean existsById(long id);
	
	void deleteById(long id);
	
	Airport findByAirportCode(String code);
	
	List<String> findAllAirportCode();
	List<String> findAllAirportName();
}
