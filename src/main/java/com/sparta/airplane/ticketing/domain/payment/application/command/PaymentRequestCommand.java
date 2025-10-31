package com.sparta.airplane.ticketing.domain.payment.application.command;

import com.sparta.airplane.ticketing.domain.payment.domain.vo.CardCompany;
import java.math.BigDecimal;

public record PaymentRequestCommand(
    Long reservationId,
    Long userId,
    String cardNumber,
    CardCompany cardCompany,
    BigDecimal cardAmount,
    BigDecimal mileageUseAmount
) {

}
