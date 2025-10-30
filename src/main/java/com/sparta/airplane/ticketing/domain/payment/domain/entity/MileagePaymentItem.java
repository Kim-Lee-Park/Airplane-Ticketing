package com.sparta.airplane.ticketing.domain.payment.domain.entity;

import com.sparta.airplane.ticketing.domain.payment.domain.vo.PaymentItemAmount;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
