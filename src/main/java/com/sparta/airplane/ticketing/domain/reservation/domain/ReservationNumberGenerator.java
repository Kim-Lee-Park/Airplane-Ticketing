package com.sparta.airplane.ticketing.domain.reservation.domain;

import com.sparta.airplane.ticketing.domain.reservation.domain.vo.ReservationNumber;

public interface ReservationNumberGenerator {
    ReservationNumber generate();
}
