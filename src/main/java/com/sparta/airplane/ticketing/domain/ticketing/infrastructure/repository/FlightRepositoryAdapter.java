package com.sparta.airplane.ticketing.domain.ticketing.infrastructure.repository;

import com.sparta.airplane.ticketing.domain.ticketing.domain.entity.Flight;
import com.sparta.airplane.ticketing.domain.ticketing.domain.repository.FlightRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FlightRepositoryAdapter implements FlightRepository {

    private final JpaFlightRepository jpaFlightRepository;

    @Override
    public Flight save(Flight flight) {
        return jpaFlightRepository.save(flight);
    }

    @Override
    public List<Flight> findSearchFlights(String departure, String arrival, String date, int passengerCount) {
        LocalDateTime departureDate = LocalDate.parse(date).atStartOfDay();
        return jpaFlightRepository.findAvailableFlights(departure, arrival, departureDate, passengerCount);
    }
}
