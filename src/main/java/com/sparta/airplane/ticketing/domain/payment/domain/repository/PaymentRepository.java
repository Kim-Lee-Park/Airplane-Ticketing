package com.sparta.airplane.ticketing.domain.payment.domain.repository;

import com.sparta.airplane.ticketing.domain.payment.domain.entity.Payment;
import java.util.Optional;

public interface PaymentRepository {

    Payment save(Payment payment);

    Optional<Payment> findById(Long paymentId);
}
