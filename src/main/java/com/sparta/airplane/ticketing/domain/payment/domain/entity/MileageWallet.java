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
}
