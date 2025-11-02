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

/**
 * 마일리지 거래 이력 Entity
 *
 * <역할>
 * - MileageWallet Aggregate 내에서 개별 마일리지 거래(적립/사용)를 기록
 * - 거래 전후 잔액, 변동 금액, 거래 유형을 추적하여 감사 추적(Audit Trail) 제공
 *
 * <설계 이유>
 * - Entity로 설계한 이유:
 *   1. 식별자(ID)가 필요하며, 각 거래는 독립적인 이력으로 관리됨
 *   2. 시간 순서대로 정렬 가능한 이력 데이터
 *
 * - Aggregate Root가 아닌 이유:
 *   MileageWallet을 통해서만 생성되고 관리되며, 독립적으로 생성되지 않음
 * - package-private 팩토리 메서드:
 *   MileageWallet에서만 생성할 수 있도록 접근 제한하여 일관성 보장
 */
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