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

/**
 * 카드 결제 항목 Entity
 *
 * <역할>
 * - Payment Aggregate 내에서 카드로 결제된 개별 결제 수단을 나타냄
 * - PG사 결제 키, 카드 번호, 카드사 정보를 관리
 *
 * <설계 이유>
 * - Entity로 설계한 이유:
 *   1. 식별자(ID)가 필요하며, PG 결제 상태 변경 등 생명주기 존재
 *   2. PaymentItem의 하위 타입으로 다형성 구현
 *
 * - Aggregate Root가 아닌 이유:
 *   Payment를 통해서만 접근되고 관리되며, 독립적으로 조회/수정되지 않음
 */
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
