package com.synergisticit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.synergisticit.domain.Airport;

public interface AirportRepository extends JpaRepository<Airport, Long> {

	Airport findByAirportCode(String code);
	
	@Query(value = "SELECT a.airportCode FROM airport a", nativeQuery=true)
	List<String> findAllAirportCode();
	
	@Query(value = "SELECT a.airportName FROM airport a", nativeQuery=true)
	List<String> findAllAirportName();
}
