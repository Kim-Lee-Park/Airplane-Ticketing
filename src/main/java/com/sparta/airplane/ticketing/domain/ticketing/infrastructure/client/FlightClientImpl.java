package com.sparta.airplane.ticketing.domain.ticketing.infrastructure.client;

import com.sparta.airplane.ticketing.domain.reservation.application.client.FlightClient;
import com.sparta.airplane.ticketing.domain.reservation.domain.vo.FlightInfo;
import com.sparta.airplane.ticketing.domain.ticketing.infrastructure.client.dto.FlightDto;

public class FlightClientImpl implements FlightClient {

    @Override
    public FlightInfo getFlightInfo(Long flightId) {
        FlightDto response = getExternalFlightInfo(flightId);

        return new FlightInfo(
            response.flightId(),
            response.airline(),
            response.departureAirport(),
            response.arrivalAirport(),
            response.flightType()
        );
    }

    /**
     * FlightInfo 더미 데이터 생성
     * @param flightId 비행 ID
     * @return FlightDto
     */
    private FlightDto getExternalFlightInfo(Long flightId) {
        return new FlightDto(
            flightId,
            "Sample Airline",
            "Incheon Airport",
            "Tokyo Airport",
            "ONE_WAY"
        );
    }
}
