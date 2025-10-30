package com.sparta.airplane.ticketing.domain.payment.infrastructure.repository;

import com.sparta.airplane.ticketing.domain.payment.domain.entity.MileageWallet;
import com.sparta.airplane.ticketing.domain.payment.domain.repository.MileageWalletRepository;
import com.sparta.airplane.ticketing.domain.payment.domain.vo.CustomerId;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MileageWalletRepositoryAdapter implements MileageWalletRepository {

    private final JpaMileageWalletRepository jpaMileageWalletRepository;


    @Override
    public Optional<MileageWallet> findByCustomerId(CustomerId customerId) {
        return jpaMileageWalletRepository.findByCustomerId(customerId);
    }

    @Override
    public Optional<MileageWallet> findById(Long id) {
        return jpaMileageWalletRepository.findById(id);
    }
}
