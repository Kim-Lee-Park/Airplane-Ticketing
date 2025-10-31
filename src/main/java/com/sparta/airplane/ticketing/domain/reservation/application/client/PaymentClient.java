package com.sparta.airplane.ticketing.domain.reservation.application.client;

public interface PaymentClient {

    /**
     * 환불 처리
     */
    void refundPayment(Long paymentId);
}
