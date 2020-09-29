package com.synergisticit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.synergisticit.domain.Flight;

public interface FlightRepository extends JpaRepository<Flight, Long> {

	@Query(value = "SELECT * FROM flight f WHERE f.departFrom_airportId = :departId AND f.arriveAt_airportId = :arriveId", nativeQuery=true)
	List<Flight> findByDepartureCode(long departId, long arriveId);
}
