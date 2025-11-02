package com.sparta.airplane.ticketing.domain.ticketing.domain.repository;

import com.sparta.airplane.ticketing.domain.ticketing.domain.entity.Flight;
import java.util.List;

/**
 * 티켓팅 도메인의 항공편 Aggregate Repository 인터페이스
 *
 * 도메인 계층에서 JPA 등의 특정 기술에 의존하지 않도록 인터페이스로 작성
 * Infrastructure 계층에서 특정 기술 주입 및 구현체 작성 후 제공
 */
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
