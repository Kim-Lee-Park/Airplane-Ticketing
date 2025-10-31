package com.sparta.airplane.ticketing.domain.reservation.application.command;

public record CancelReservationCommand(
    String reservationNumber,
    Long paymentId
) {

    public static CancelReservationCommand of(String reservationNumber, Long paymentId) {
        return new CancelReservationCommand(reservationNumber, paymentId);
    }
}
