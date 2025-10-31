package com.sparta.airplane.ticketing.domain.reservation.presentation.request;

import com.sparta.airplane.ticketing.domain.reservation.application.command.CreateReservationCommand;
import com.sparta.airplane.ticketing.domain.reservation.application.command.PassengerCommand;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.List;

public record CreateReservationRequest(
    @NotNull(message = "flightId는 필수입니다")
    @Positive(message = "flightId는 양수여야 합니다")
    Long flightId,
    @NotEmpty(message = "승객 정보는 최소 한 명 이상이어야 합니다")
    @Valid
    List<PassengerRequest> passengers
) {
    public CreateReservationCommand toCommand() {
        List<PassengerCommand> passengerCommands = passengers.stream()
            .map(PassengerRequest::toCommand)
            .toList();

        return new CreateReservationCommand(
            flightId,
            passengerCommands
        );
    }
}
