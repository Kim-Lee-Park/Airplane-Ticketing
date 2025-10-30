package com.sparta.airplane.ticketing.domain.payment.domain.entity;

import com.sparta.airplane.ticketing.domain.payment.domain.vo.CardCompany;
import com.sparta.airplane.ticketing.domain.payment.domain.vo.PaymentItemAmount;
import com.sparta.airplane.ticketing.domain.payment.domain.vo.PaymentStatus;
import com.sparta.airplane.ticketing.domain.payment.domain.vo.PaymentUserId;
import com.sparta.airplane.ticketing.domain.payment.domain.vo.ReservationId;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.AbstractAggregateRoot;

@Entity
@Table(name = "payments")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Payment extends AbstractAggregateRoot<Payment> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal totalAmount;

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "reservation_id"))
    private ReservationId reservationId;
    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "user_id"))
    private PaymentUserId paymentUserId;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @OneToMany(mappedBy = "payment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PaymentItem> paymentItems = new ArrayList<>();

    public static Payment create(
        ReservationId reservationId,
        PaymentUserId paymentUserId
    ) {
        Payment payment = new Payment();
        payment.totalAmount = BigDecimal.ZERO;
        payment.reservationId = reservationId;
        payment.paymentUserId = paymentUserId;
        payment.paymentStatus = PaymentStatus.PENDING;

        return payment;
    }

    public void addCardPayment(
        PaymentItemAmount cardAmount,
        String cardNumber,
        CardCompany cardCompany
    ) {
        validatePaymentAmount(cardAmount);

        this.totalAmount = this.totalAmount.add(cardAmount.getAmount());

        // 임시 PG 결제 키 생성
        CardPaymentItem cardItem = CardPaymentItem.builder()
            .amount(cardAmount)
            .payment(this)
            .pgPaymentKey("TEMP_PG_KEY")
            .cardNumber(cardNumber)
            .cardCompany(cardCompany)
            .build();

        this.paymentItems.add(cardItem);
    }

    public void useMileage(PaymentItemAmount mileageAmount, Long mileageWalletId) {
        validatePaymentAmount(mileageAmount);

        this.totalAmount = this.totalAmount.add(mileageAmount.getAmount());

        MileagePaymentItem mileagePaymentItem = MileagePaymentItem.create(
            mileageAmount,
            this,
            mileageWalletId
        );

        this.paymentItems.add(mileagePaymentItem);
    }

    private void validatePaymentAmount(PaymentItemAmount amount) {
        BigDecimal currentTotal = paymentItems.stream()
            .map(item -> item.getAmount().getAmount())
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal newTotal = currentTotal.add(amount.getAmount());

        if (newTotal.compareTo(totalAmount) > 0) {
            throw new IllegalArgumentException("결제 금액 합이 총 금액을 초과할 수 없습니다");
        }
    }

    public void complete() {
        BigDecimal itemsTotal = paymentItems.stream()
            .map(item -> item.getAmount().getAmount())
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        if (itemsTotal.compareTo(totalAmount) != 0) {
            throw new IllegalStateException("결제 금액이 일치하지 않습니다");
        }

        this.paymentStatus = PaymentStatus.COMPLETED;
    }
}
