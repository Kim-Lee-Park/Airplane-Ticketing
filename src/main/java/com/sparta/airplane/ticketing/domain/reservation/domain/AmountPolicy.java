package com.sparta.airplane.ticketing.domain.reservation.domain;

import java.math.BigDecimal;

public interface AmountPolicy {
    BigDecimal calculate();
}
