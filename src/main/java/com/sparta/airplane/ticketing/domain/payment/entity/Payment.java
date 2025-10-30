package com.sparta.airplane.ticketing.domain.payment.entity;

import com.sparta.airplane.ticketing.domain.payment.vo.PaymentStatus;
import com.sparta.airplane.ticketing.domain.payment.vo.PaymentUserId;
import com.sparta.airplane.ticketing.domain.payment.vo.ReservationId;
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
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
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
    private List<PaymentItem> paymentItems;

    @Builder
    public Payment(BigDecimal totalAmount, ReservationId reservationId, PaymentUserId paymentUserId) {
        // 검증
        this.totalAmount = totalAmount;
        this.reservationId = reservationId;
        this.paymentUserId = paymentUserId;
        this.paymentStatus = PaymentStatus.PENDING;
    }
}
