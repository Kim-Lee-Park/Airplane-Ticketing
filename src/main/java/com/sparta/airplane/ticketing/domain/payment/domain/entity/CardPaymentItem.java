package com.sparta.airplane.ticketing.domain.payment.domain.entity;

import com.sparta.airplane.ticketing.domain.payment.domain.vo.CardCompany;
import com.sparta.airplane.ticketing.domain.payment.domain.vo.PaymentItemAmount;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "card_payment_items")
@DiscriminatorValue("CARD")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CardPaymentItem extends PaymentItem {

    private String pgPaymentKey;
    private String cardNumber;
    @Enumerated(EnumType.STRING)
    private CardCompany cardCompany;

    @Builder
    public CardPaymentItem(PaymentItemAmount amount, Payment payment, String pgPaymentKey, String cardNumber, CardCompany cardCompany) {
        // 검증
        super(amount, payment);
        this.pgPaymentKey = pgPaymentKey;
        this.cardNumber = cardNumber;
        this.cardCompany = cardCompany;
    }
}
