package com.sparta.airplane.ticketing.domain.reservation.infrastructure.repository;

import com.sparta.airplane.ticketing.domain.reservation.domain.entity.Reservation;
import com.sparta.airplane.ticketing.domain.reservation.domain.repository.ReservationRepository;
import com.sparta.airplane.ticketing.domain.reservation.domain.vo.ReservationNumber;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReservationRepositoryAdapter implements ReservationRepository {

    private final JpaReservationRepository jpaReservationRepository;

    @Override
    public Reservation save(Reservation reservation) {
        return jpaReservationRepository.save(reservation);
    }

    @Override
    public Optional<Reservation> findByReservationNumber(ReservationNumber reservationNumber) {
        return jpaReservationRepository.findByReservationNumber(reservationNumber);
    }
}
