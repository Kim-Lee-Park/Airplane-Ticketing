package com.sparta.airplane.ticketing.domain.ticketing.infrastructure.repository;

import com.sparta.airplane.ticketing.domain.ticketing.domain.entity.Flight;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaFlightRepository extends JpaRepository<Flight, Long> {

    @Query("""
          SELECT f FROM Flight f
          WHERE f.departureAirport.code = :departure
          AND f.arrivalAirport.code = :arrival
          AND DATE(f.departureTime) = DATE(:date)
          AND f.availableSeats >= :passengerCount
          AND f.status = 'SCHEDULED'
          """)
    List<Flight> findAvailableFlights(
        @Param("departure") String departure,
        @Param("arrival") String arrival,
        @Param("date") LocalDateTime date,
        @Param("passengerCount") int passengerCount
    );
}
