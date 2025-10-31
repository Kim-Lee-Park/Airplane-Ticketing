package com.sparta.airplane.ticketing.domain.reservation.presentation.request;

import com.sparta.airplane.ticketing.domain.reservation.application.command.ConfirmReservationCommand;
import jakarta.validation.constraints.NotNull;

public record ConfirmReservationRequest(
    @NotNull(message = "결제 ID는 필수입니다")
    Long paymentId
) {
    public ConfirmReservationCommand toCommand(String reservationNumber) {
        return new ConfirmReservationCommand(paymentId, reservationNumber);
    }
}
