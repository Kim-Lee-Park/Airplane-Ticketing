package com.sparta.airplane.ticketing.domain.reservation.domain.vo;

/**
 * 항공편 정보 값 객체
 * - record를 통한 불변성 보장
 * - ticketing 컨텍스트에서 항공편 관련 정보를 받아오는 용도의 객체
 */
public record FlightInfo(
    Long flightId,
    String airline,
    String departureAirport,
    String arrivalAirport,
    String flightType
) {

    public RouteInfo toRouteInfo() {
        return new RouteInfo(
            this.departureAirport,
            this.arrivalAirport,
            RouteType.valueOf(this.flightType)
        );
    }
}
