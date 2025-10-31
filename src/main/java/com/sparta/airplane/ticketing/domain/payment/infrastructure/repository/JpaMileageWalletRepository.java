package com.sparta.airplane.ticketing.domain.payment.infrastructure.repository;

import com.sparta.airplane.ticketing.domain.payment.domain.entity.MileageWallet;
import com.sparta.airplane.ticketing.domain.payment.domain.vo.CustomerId;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaMileageWalletRepository extends JpaRepository<MileageWallet, Long> {

    Optional<MileageWallet> findByCustomerId(CustomerId customerId);
}
