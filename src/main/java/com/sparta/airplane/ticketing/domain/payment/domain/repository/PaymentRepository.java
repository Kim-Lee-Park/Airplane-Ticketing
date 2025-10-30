package com.sparta.airplane.ticketing.domain.payment.domain.repository;

import com.sparta.airplane.ticketing.domain.payment.domain.entity.Payment;

public interface PaymentRepository {

    Payment save(Payment payment);
}
