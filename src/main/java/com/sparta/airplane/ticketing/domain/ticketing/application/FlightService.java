package com.sparta.airplane.ticketing.domain.ticketing.application;

import com.sparta.airplane.ticketing.domain.ticketing.domain.entity.Flight;
import com.sparta.airplane.ticketing.domain.ticketing.domain.repository.FlightRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class FlightService {
    private final FlightRepository flightRepository;

    public List<Flight> searchFlights(String departure, String arrival, String date, int passengerCount) {
        log.info("항공편 검색: 출발지 [{}], 도착지 [{}], 출발 날짜 [{}] 탑승 인원 [{}]", departure, arrival, date, passengerCount);
        return flightRepository.findSearchFlights(departure, arrival, date, passengerCount);
    }
}
