package com.sparta.airplane.ticketing.domain.ticketing.domain.repository;

import com.sparta.airplane.ticketing.domain.ticketing.domain.entity.Flight;
import java.util.List;

public interface FlightRepository {

    /**
     * 항공편 저장
     * @param flight 저장할 항공편
     * @return 저장된 항공편
     */
    Flight save(Flight flight);

    /**
     * 항공편 검색
     * @param departure 출발지
     * @param arrival 도착지
     * @param date 출발 날짜
     * @param passengerCount 탑승 인원
     * @return 검색된 항공편 리스트
     */
    List<Flight> findSearchFlights(String departure, String arrival, String date, int passengerCount);
}
