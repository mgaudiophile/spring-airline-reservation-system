package com.synergisticit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.synergisticit.domain.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {

	@Query(value = "SELECT * FROM passenger p WHERE p.ticketId = :ticketId", nativeQuery=true)
	List<Passenger> findAllByTicketId(long ticketId);
}
