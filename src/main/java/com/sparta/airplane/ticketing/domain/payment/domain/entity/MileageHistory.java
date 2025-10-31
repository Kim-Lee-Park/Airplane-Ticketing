package com.sparta.airplane.ticketing.domain.payment.domain.entity;

import com.sparta.airplane.ticketing.domain.payment.domain.vo.MileageTransactionType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "mileage_histories")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MileageHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal previousBefore;
    private BigDecimal changeAmount;
    private BigDecimal afterBalance;

    @Enumerated(EnumType.STRING)
    private MileageTransactionType transactionType;
    @ManyToOne(fetch = FetchType.LAZY)
    private MileageWallet mileageWallet;

    // package-private 적용(MileageWallet에서만 만들 수 있도록 의도)
    static MileageHistory create(
        BigDecimal previousBefore,
        BigDecimal changeAmount,
        BigDecimal afterBalance,
        MileageTransactionType transactionType,
        MileageWallet mileageWallet
    ) {
        MileageHistory history = new MileageHistory();
        history.previousBefore = previousBefore;
        history.changeAmount = changeAmount;
        history.afterBalance = afterBalance;
        history.transactionType = transactionType;
        history.mileageWallet = mileageWallet;
        return history;
    }
}