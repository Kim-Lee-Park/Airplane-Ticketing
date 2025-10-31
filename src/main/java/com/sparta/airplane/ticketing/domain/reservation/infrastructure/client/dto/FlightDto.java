package com.sparta.airplane.ticketing.domain.reservation.infrastructure.client.dto;

/**
 * 외부 Flight DTO 클래스
 */
public record FlightDto(
    Long flightId,
    String airline,
    String departureAirport,
    String arrivalAirport,
    String flightType
) {

}