package com.sparta.airplane.ticketing.domain.payment.domain.repository;

import com.sparta.airplane.ticketing.domain.payment.domain.entity.MileageWallet;
import com.sparta.airplane.ticketing.domain.payment.domain.vo.CustomerId;
import java.util.Optional;

public interface MileageWalletRepository {

    Optional<MileageWallet> findByCustomerId(CustomerId customerId);

    Optional<MileageWallet> findById(Long id);
}
