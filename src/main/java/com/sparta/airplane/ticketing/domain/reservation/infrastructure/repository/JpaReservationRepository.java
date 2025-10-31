package com.sparta.airplane.ticketing.domain.reservation.infrastructure.repository;

import com.sparta.airplane.ticketing.domain.reservation.domain.entity.Reservation;
import com.sparta.airplane.ticketing.domain.reservation.domain.vo.ReservationNumber;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaReservationRepository extends JpaRepository<Reservation, Long> {

    Optional<Reservation> findByReservationNumber(ReservationNumber reservationNumber);
}
