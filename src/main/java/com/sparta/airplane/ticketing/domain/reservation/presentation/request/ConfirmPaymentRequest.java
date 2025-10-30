package com.sparta.airplane.ticketing.domain.reservation.presentation.request;

import jakarta.validation.constraints.NotNull;

public record ConfirmPaymentRequest(
    @NotNull(message = "결제 ID는 필수입니다")
    Long paymentId
) {

}
