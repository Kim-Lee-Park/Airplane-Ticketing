package com.sparta.airplane.ticketing.domain.payment.entity;

import com.sparta.airplane.ticketing.domain.payment.vo.MileageTransactionType;
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
    private BigDecimal currentBalance;

    @Enumerated(EnumType.STRING)
    private MileageTransactionType transactionType;
    @ManyToOne(fetch = FetchType.LAZY)
    private MileageWallet mileageWallet;
}