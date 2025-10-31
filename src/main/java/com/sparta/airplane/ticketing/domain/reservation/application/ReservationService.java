package com.sparta.airplane.ticketing.domain.reservation.application;

import com.sparta.airplane.ticketing.domain.reservation.application.client.FlightClient;
import com.sparta.airplane.ticketing.domain.reservation.application.client.PaymentClient;
import com.sparta.airplane.ticketing.domain.reservation.application.command.CancelReservationCommand;
import com.sparta.airplane.ticketing.domain.reservation.application.command.ConfirmReservationCommand;
import com.sparta.airplane.ticketing.domain.reservation.application.command.CreateReservationCommand;
import com.sparta.airplane.ticketing.domain.reservation.domain.ReservationNumberGenerator;
import com.sparta.airplane.ticketing.domain.reservation.domain.entity.Reservation;
import com.sparta.airplane.ticketing.domain.reservation.domain.repository.ReservationRepository;
import com.sparta.airplane.ticketing.domain.reservation.domain.vo.FlightInfo;
import com.sparta.airplane.ticketing.domain.reservation.domain.vo.Passenger;
import com.sparta.airplane.ticketing.domain.reservation.domain.vo.ReservationNumber;
import com.sparta.airplane.ticketing.domain.reservation.domain.vo.RouteInfo;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final ReservationNumberGenerator reservationNumberGenerator;
    private final FlightClient flightClient;
    private final PaymentClient paymentClient;

    public Reservation createReservation(CreateReservationCommand command) {
        FlightInfo flightInfo = flightClient.getFlightInfo(command.flightId());

        RouteInfo routeInfo = flightInfo.toRouteInfo();

        List<Passenger> passengerList = command.toPassengers();

        Reservation reservation = Reservation.create(
            flightInfo.airline(),
            routeInfo,
            passengerList,
            reservationNumberGenerator
        );

        return reservationRepository.save(reservation);
    }

    @Transactional
    public void confirmReservation(ConfirmReservationCommand command) {
        ReservationNumber number = ReservationNumber.of(command.reservationNumber());
        Reservation reservation = reservationRepository.findByReservationNumber(number)
            .orElseThrow(() -> new IllegalArgumentException("예약을 찾을 수 없습니다"));
        reservation.confirm();
        reservationRepository.save(reservation);
    }

    @Transactional
    public void cancelReservation(CancelReservationCommand command) {
        ReservationNumber number = ReservationNumber.of(command.reservationNumber());
        Reservation reservation = reservationRepository.findByReservationNumber(number)
            .orElseThrow(() -> new IllegalArgumentException("예약을 찾을 수 없습니다"));

        if (reservation.isConfirmed()) {
            paymentClient.refundPayment(command.paymentId());
        }

        reservation.cancel();
    }
}
