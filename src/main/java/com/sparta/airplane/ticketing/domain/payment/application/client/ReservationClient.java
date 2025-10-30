package com.sparta.airplane.ticketing.domain.payment.application.client;

import org.springframework.stereotype.Component;

@Component
public interface ReservationClient {

    /**
     * 결제 완료 시 예약을 확정으로 변경시키도록
     * Post 요청
     */
    void confirmPayment(Long paymentId);
}
