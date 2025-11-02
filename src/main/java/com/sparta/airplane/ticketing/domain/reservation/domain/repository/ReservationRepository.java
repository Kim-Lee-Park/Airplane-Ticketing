package com.sparta.airplane.ticketing.domain.reservation.domain.repository;

import com.sparta.airplane.ticketing.domain.reservation.domain.entity.Reservation;
import com.sparta.airplane.ticketing.domain.reservation.domain.vo.ReservationNumber;
import java.util.Optional;

/**
 * 예약 도메인 Repository 인터페이스
 *
 * 도메인 계층에서 JPA 등의 특정 기술에 의존하지 않도록 인터페이스로 작성
 * Infrastructure 계층에서 특정 기술 주입 및 구현체 작성 후 제공
 */
public interface ReservationRepository {

    /**
     * 예약 저장
     * @param reservation 예약 정보
     * @return 저장된 예약 정보
     */
    Reservation save(Reservation reservation);

    Optional<Reservation> findByReservationNumber(ReservationNumber reservationNumber);
}
