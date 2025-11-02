package com.sparta.airplane.ticketing.domain.payment.domain.entity;

import com.sparta.airplane.ticketing.domain.payment.domain.vo.PaymentItemAmount;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 마일리지 결제 항목 Entity
 *
 * <역할>
 * - Payment Aggregate 내에서 마일리지로 결제된 개별 결제 수단을 나타냄
 * - 어느 마일리지 지갑(MileageWallet)에서 차감되었는지 참조 ID 관리
 *
 * <설계 이유>
 * - Entity로 설계한 이유:
 *   식별자(ID)가 필요하며, PaymentItem의 하위 타입으로 다형성 구현
 *
 * - Aggregate Root가 아닌 이유:
 *   Payment를 통해서만 접근되고 관리되며, 독립적으로 조회/수정되지 않음
 * - MileageWallet을 ID로만 참조하는 이유:
 *   Payment와 MileageWallet은 별도의 Aggregate이므로 간접 참조 사용
 */
@Entity
@Getter
@Table(name = "mileage_payment_items")
@DiscriminatorValue("MILEAGE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MileagePaymentItem extends PaymentItem {

    private Long mileageWalletId;

    private MileagePaymentItem(
        PaymentItemAmount amount,
        Payment payment,
        Long mileageWalletId
    ) {
        super(amount, payment);
        this.mileageWalletId = mileageWalletId;
    }

    public static MileagePaymentItem create(
        PaymentItemAmount amount,
        Payment payment,
        Long mileageWalletId
    ) {
        return new MileagePaymentItem(amount, payment, mileageWalletId);
    }

}
