package com.sparta.airplane.ticketing.domain.payment.entity;

import com.sparta.airplane.ticketing.domain.payment.vo.Balance;
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
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
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
    @Embedded
    @AttributeOverride(name = "amount", column = @Column(name = "mileage_balance"))
    private Balance balance;

    @OneToMany(mappedBy = "mileage", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MileageHistory> mileageHistories;

    @Builder
    public MileageWallet(Balance balance, List<MileageHistory> mileageHistories) {
        this.balance = balance;
        this.mileageHistories = mileageHistories;
    }
}
