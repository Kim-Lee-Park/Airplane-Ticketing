package com.sparta.airplane.ticketing.domain.reservation.infrastructure.client;

import com.sparta.airplane.ticketing.domain.reservation.application.client.PaymentClient;

public class PaymentClientImpl implements PaymentClient {

    @Override
    public void refundPayment(Long paymentId) {
        // 실제 요청은 webclient로 delete 요청..
        // 이벤트로 변경하는 것이 제일 좋아보임
    }
}
