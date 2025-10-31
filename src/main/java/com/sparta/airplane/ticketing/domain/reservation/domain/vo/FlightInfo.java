package com.sparta.airplane.ticketing.domain.reservation.domain.vo;

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
