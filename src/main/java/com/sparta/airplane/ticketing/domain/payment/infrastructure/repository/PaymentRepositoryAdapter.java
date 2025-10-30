package com.sparta.airplane.ticketing.domain.payment.infrastructure.repository;

import com.sparta.airplane.ticketing.domain.payment.domain.entity.Payment;
import com.sparta.airplane.ticketing.domain.payment.domain.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentRepositoryAdapter implements PaymentRepository {

    private final JpaPaymentRepository jpaPaymentRepository;

    @Override
    public Payment save(Payment payment) {
        return jpaPaymentRepository.save(payment);
    }
}
