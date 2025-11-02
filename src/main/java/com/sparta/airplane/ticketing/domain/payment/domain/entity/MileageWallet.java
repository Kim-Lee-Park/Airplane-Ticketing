package com.sparta.airplane.ticketing.domain.payment.domain.entity;

import com.sparta.airplane.ticketing.domain.payment.domain.vo.Balance;
import com.sparta.airplane.ticketing.domain.payment.domain.vo.CustomerId;
import com.sparta.airplane.ticketing.domain.payment.domain.vo.MileageTransactionType;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
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

/**
 * 마일리지 지갑 Aggregate Root
 *
 * <역할>
 * - 고객의 마일리지 잔액 및 거래 이력(적립/사용)을 관리하는 핵심 도메인 객체
 * - 마일리지 적립(earn), 사용(deduct) 비즈니스 규칙 강제
 * - 잔액 부족 검증 및 거래 이력 자동 생성
 *
 * <설계 이유>
 * - Aggregate Root로 설계한 이유:
 *   1. 마일리지 지갑은 독립적인 비즈니스 경계를 가지며, 잔액 변경 시 일관성 보장 필요
 *   2. 거래 이력(MileageHistory)은 지갑을 통해서만 생성되고 관리되어야 함
 *   3. 잔액 차감 가능 여부 같은 비즈니스 규칙이 지갑 컨텍스트 내에서 결정됨
 *
 * <Aggregate 경계>
 * - MileageWallet (루트)
 * - MileageHistory (자식 Entity)
 * - Balance, CustomerId, MileageTransactionType (값 객체)
 */
@Entity
@Table(name = "mileages")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MileageWallet extends AbstractAggregateRoot<MileageWallet> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 지갑 주인 고객 간접 참조
    @Embedded
    private CustomerId customerId;

    @Embedded
    @AttributeOverride(name = "amount", column = @Column(name = "mileage_balance"))
    private Balance balance;

    @OneToMany(mappedBy = "mileage", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MileageHistory> mileageHistories = new ArrayList<>();


    private static MileageWallet create(Balance balance, List<MileageHistory> mileageHistories) {
        MileageWallet mileageWallet = new MileageWallet();
        mileageWallet.balance = balance;
        mileageWallet.mileageHistories = mileageHistories;
        return mileageWallet;
    }

    public boolean isOwnedBy(Long id) {
        return this.customerId.getId().equals(id);
    }

    public void deduct(BigDecimal mileageAmount) {
        BigDecimal previousBalance = this.balance.getAmount();

        if (previousBalance.compareTo(mileageAmount) < 0) {
            throw new IllegalArgumentException("마일리지 잔액이 부족합니다");
        }

        BigDecimal afterBalance = previousBalance.subtract(mileageAmount);

        this.balance = Balance.of(afterBalance);

        MileageHistory history = MileageHistory.create(
            previousBalance,
            mileageAmount,
            afterBalance,
            MileageTransactionType.USE,
            this
        );

        this.mileageHistories.add(history);
    }

    public void earn(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("적립 금액은 0보다 커야 합니다");
        }

        BigDecimal previousBalance = this.balance.getAmount();

        BigDecimal afterBalance = previousBalance.add(amount);

        this.balance = Balance.of(afterBalance);

        MileageHistory history = MileageHistory.create(
            previousBalance,
            amount,
            afterBalance,
            MileageTransactionType.EARN,
            this
        );

        this.mileageHistories.add(history);
    }
}
