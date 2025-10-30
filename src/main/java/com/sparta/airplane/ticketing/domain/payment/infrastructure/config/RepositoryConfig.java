package com.sparta.airplane.ticketing.domain.payment.infrastructure.config;

import com.sparta.airplane.ticketing.domain.payment.domain.repository.PaymentRepository;
import com.sparta.airplane.ticketing.domain.payment.infrastructure.repository.JpaMileageWalletRepository;
import com.sparta.airplane.ticketing.domain.payment.infrastructure.repository.JpaPaymentRepository;
import com.sparta.airplane.ticketing.domain.payment.infrastructure.repository.MileageWalletRepositoryAdapter;
import com.sparta.airplane.ticketing.domain.payment.infrastructure.repository.PaymentRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfig {

    @Bean
    public PaymentRepository paymentRepository(JpaPaymentRepository jpaPaymentRepository) {
        return new PaymentRepositoryAdapter(jpaPaymentRepository);
    }

    @Bean
    public MileageWalletRepositoryAdapter mileageWalletRepository(JpaMileageWalletRepository jpaMileageWalletRepository) {
        return new MileageWalletRepositoryAdapter(jpaMileageWalletRepository);
    }
}
