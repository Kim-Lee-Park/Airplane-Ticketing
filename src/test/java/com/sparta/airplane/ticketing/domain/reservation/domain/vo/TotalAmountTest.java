package com.sparta.airplane.ticketing.domain.reservation.domain.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class TotalAmountTest {

    @Test
    @DisplayName("금액은 null 일 수 없습니다.")
    void amountIsNull() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new TotalAmount(null)
        );
    }

    @Test
    @DisplayName("금액은 0보다 커야 합니다")
    void zeroAmount() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new TotalAmount(BigDecimal.ZERO)
        );
    }
}
