package com.sparta.airplane.ticketing.domain.payment.domain.repository;

import com.sparta.airplane.ticketing.domain.payment.domain.entity.Payment;
import java.util.Optional;

/**
 * 결제 도메인의 결제 Aggregate Repository 인터페이스
 *
 * 도메인 계층에서 JPA 등의 특정 기술에 의존하지 않도록 인터페이스로 작성
 * Infrastructure 계층에서 특정 기술 주입 및 구현체 작성 후 제공
 */
public interface PaymentRepository {

    Payment save(Payment payment);

    Optional<Payment> findById(Long paymentId);
}
