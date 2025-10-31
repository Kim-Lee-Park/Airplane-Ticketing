package com.sparta.airplane.ticketing.domain.reservation.application.client;

import com.sparta.airplane.ticketing.domain.reservation.domain.vo.FlightInfo;
import org.springframework.stereotype.Component;

@Component
public interface FlightClient {

    /**
     * 항공편 정보 조회
     * GET 요청
     * @param flightId 항공편 ID
     * @return 항공편 정보
     */
    FlightInfo getFlightInfo(Long flightId);
}
