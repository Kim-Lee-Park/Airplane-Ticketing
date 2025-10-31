package com.sparta.airplane.ticketing.domain.reservation.application.command;

public record ConfirmReservationCommand(
    Long paymentId,
    String reservationNumber
) {

}
