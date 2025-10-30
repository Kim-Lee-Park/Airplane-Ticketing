package com.sparta.airplane.ticketing.domain.payment.entity;

import com.sparta.airplane.ticketing.domain.payment.vo.PaymentItemAmount;
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

    public MileagePaymentItem(PaymentItemAmount amount, Payment payment) {
        super(amount, payment);
    }

}
