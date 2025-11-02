package com.sparta.airplane.ticketing.domain.payment.domain.entity;

import com.sparta.airplane.ticketing.domain.payment.domain.vo.PaymentItemAmount;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 결제 항목 추상 Entity
 *
 * <역할>
 * - Payment Aggregate 내에서 개별 결제 수단을 나타내는 기본 Entity
 * - 카드 결제(CardPaymentItem), 마일리지 결제(MileagePaymentItem)의 공통 속성과 행위 정의
 *
 * <설계 이유>
 * - 추상 Entity로 설계한 이유:
 *   1. 다양한 결제 수단에 대해 다형성 및 확장성을 가져가기 위함
 *   2. 결제 금액과 Payment 엔티티 참조는 모든 결제 수단에 공통적으로 필요함
 *
 * - JOINED 상속 전략 사용 이유:
 *   SINGLE_TABLE 상속 전략 사용 시, 하나의 테이블로 모든 컬럼을 관리
 *   결제 수단별로 불필요한 컬럼에 null이 들어가는 문제 발생
 *   JOINED 상속 전략을 통해 각각 별개의 테이블로 관리
 *
 * - Entity인 이유:
 *   식별자가 필요하며, Payment Aggregate 내에서만 관리됨
 */
@Entity
@Table(name = "payment_items")
@Getter
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "method_type")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class PaymentItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    @AttributeOverride(name = "amount", column = @Column(name = "amount"))
    private PaymentItemAmount amount;
    @ManyToOne(fetch = FetchType.LAZY)
    private Payment payment;

    protected PaymentItem(PaymentItemAmount amount, Payment payment) {
        // 검증
        this.amount = amount;
        this.payment = payment;
    }
}
