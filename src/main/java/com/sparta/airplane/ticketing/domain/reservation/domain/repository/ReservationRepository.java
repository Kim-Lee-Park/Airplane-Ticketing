package com.sparta.airplane.ticketing.domain.reservation.domain.repository;

import com.sparta.airplane.ticketing.domain.reservation.domain.entity.Reservation;
import com.sparta.airplane.ticketing.domain.reservation.domain.vo.ReservationNumber;
import java.util.Optional;

public interface ReservationRepository {

    /**
     * 예약 저장
     * @param reservation 예약 정보
     * @return 저장된 예약 정보
     */
    Reservation save(Reservation reservation);

    Optional<Reservation> findByReservationNumber(ReservationNumber reservationNumber);
}
