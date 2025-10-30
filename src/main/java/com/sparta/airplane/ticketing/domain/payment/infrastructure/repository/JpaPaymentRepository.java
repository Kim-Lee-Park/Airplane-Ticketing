package com.sparta.airplane.ticketing.domain.payment.infrastructure.repository;

import com.sparta.airplane.ticketing.domain.payment.domain.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaPaymentRepository extends JpaRepository<Payment, Long> {

}
