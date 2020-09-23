package com.synergisticit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.synergisticit.domain.Flight;

public interface FlightRepository extends JpaRepository<Flight, Long> {

}
