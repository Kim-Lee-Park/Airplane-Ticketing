package com.sparta.airplane.ticketing.domain.payment.presentation.request;

import com.sparta.airplane.ticketing.domain.payment.application.command.PaymentRequestCommand;
import com.sparta.airplane.ticketing.domain.payment.domain.vo.CardCompany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public record PaymentRequest(
    @NotNull(message = "예약 ID는 필수입니다.")
    Long reservationId,
    @NotNull(message = "사용자 ID는 필수입니다.")
    Long userId,
    @NotBlank(message = "카드 번호는 필수입니다")
    @Pattern(regexp = "^\\d{16}$", message = "카드 번호는 16자리 숫자여야 합니다.")
    String cardNumber,
    @NotNull(message = "카드사는 필수입니다")
    CardCompany cardCompany,
    @NotNull(message = "카드 결제 금액은 필수입니다.")
    @Positive(message = "카드 결제 금액은 양수여야 합니다.")
    BigDecimal cardAmount,
    BigDecimal mileageUseAmount
) {

    public PaymentRequestCommand toCommand() {

        return new PaymentRequestCommand(
            this.reservationId,
            this.userId,
            this.cardNumber,
            this.cardCompany,
            this.cardAmount,
            this.mileageUseAmount
        );
    }
}
