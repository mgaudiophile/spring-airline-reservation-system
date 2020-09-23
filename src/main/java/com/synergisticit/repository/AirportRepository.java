package com.synergisticit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.synergisticit.domain.Airport;

public interface AirportRepository extends JpaRepository<Airport, Long> {

	@Query(value = "SELECT * FROM airport a WHERE a.city LIKE %:keyword% OR a.airportCode LIKE %:keyword%", nativeQuery=true)
	List<Airport> search(@Param("keyword") String keyword);
}
