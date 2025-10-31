package com.sparta.airplane.ticketing.domain.reservation.application.command;

import com.sparta.airplane.ticketing.domain.reservation.domain.vo.Passenger;
import java.util.List;

public record CreateReservationCommand(
    Long flightId,
    List<PassengerCommand> passengers
) {

    public List<Passenger> toPassengers() {
        return passengers.stream()
            .map(PassengerCommand::toDomain)
            .toList();
    }
}
