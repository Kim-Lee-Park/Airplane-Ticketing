package com.sparta.airplane.ticketing.domain.reservation.domain.vo;

import com.sparta.airplane.ticketing.domain.reservation.domain.vo.ReservationNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReservationNumberTest {

    @Test
    @DisplayName("예약 번호는 필수입니다")
    void emptyValue() {
        assertThrows(IllegalArgumentException.class, () -> new ReservationNumber(""));
    }
}
