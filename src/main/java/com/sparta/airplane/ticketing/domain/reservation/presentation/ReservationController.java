package com.sparta.airplane.ticketing.domain.reservation.presentation;

import com.sparta.airplane.ticketing.domain.reservation.application.ReservationService;
import com.sparta.airplane.ticketing.domain.reservation.application.command.CancelReservationCommand;
import com.sparta.airplane.ticketing.domain.reservation.application.command.ConfirmReservationCommand;
import com.sparta.airplane.ticketing.domain.reservation.application.command.CreateReservationCommand;
import com.sparta.airplane.ticketing.domain.reservation.domain.entity.Reservation;
import com.sparta.airplane.ticketing.domain.reservation.presentation.request.ConfirmReservationRequest;
import com.sparta.airplane.ticketing.domain.reservation.presentation.request.CreateReservationRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping("/create")
    public ResponseEntity<?> createReservation(@Valid  @RequestBody CreateReservationRequest request) {
        CreateReservationCommand command = request.toCommand();

        Reservation reservation = reservationService.createReservation(command);
        return ResponseEntity.ok().body(reservation);
    }

    @PatchMapping("/{reservationNumber}/confirm")
    public ResponseEntity<?> confirmReservation(
        @PathVariable String reservationNumber,
        @RequestBody ConfirmReservationRequest request
    ) {
        ConfirmReservationCommand command = request.toCommand(reservationNumber);

        reservationService.confirmReservation(command);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{reservationNumber}/cancel")
    public ResponseEntity<?> cancelReservation(
        @PathVariable String reservationNumber,
        @RequestParam Long paymentId
    ) {
        CancelReservationCommand command = CancelReservationCommand.of(reservationNumber, paymentId);

        reservationService.cancelReservation(command);

        return ResponseEntity.ok().build();
    }
}
