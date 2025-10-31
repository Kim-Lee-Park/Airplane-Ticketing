package com.sparta.airplane.ticketing.domain.payment.application;

import com.sparta.airplane.ticketing.domain.payment.application.client.ReservationClient;
import com.sparta.airplane.ticketing.domain.payment.application.command.PaymentRequestCommand;
import com.sparta.airplane.ticketing.domain.payment.domain.entity.CardPaymentItem;
import com.sparta.airplane.ticketing.domain.payment.domain.entity.MileagePaymentItem;
import com.sparta.airplane.ticketing.domain.payment.domain.entity.MileageWallet;
import com.sparta.airplane.ticketing.domain.payment.domain.entity.Payment;
import com.sparta.airplane.ticketing.domain.payment.domain.repository.MileageWalletRepository;
import com.sparta.airplane.ticketing.domain.payment.domain.repository.PaymentRepository;
import com.sparta.airplane.ticketing.domain.payment.domain.vo.CustomerId;
import com.sparta.airplane.ticketing.domain.payment.domain.vo.PaymentItemAmount;
import com.sparta.airplane.ticketing.domain.payment.domain.vo.PaymentUserId;
import com.sparta.airplane.ticketing.domain.payment.domain.vo.ReservationId;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final MileageWalletRepository mileageWalletRepository;
    private final ReservationClient reservationClient;

    /**
     * 결제 요청(생성)
     * @param command 결제 요청 커맨드
     * @return 결제 Id
     */
    @Transactional
    public Long requestPayment(PaymentRequestCommand command) {

        ReservationId reservationId = ReservationId.of(command.reservationId());
        PaymentUserId paymentUserId = PaymentUserId.of(command.userId());

        Payment payment = Payment.create(reservationId, paymentUserId);

        // 마일리지 사용
        if (useMileage(command)) {
            processMileagePayment(payment, command);
        }

        // 카드 결제
        PaymentItemAmount cardAmount = PaymentItemAmount.of(command.cardAmount());
        payment.addCardPayment(cardAmount, command.cardNumber(), command.cardCompany());

        Payment saved = paymentRepository.save(payment);

        // 트랜잭션 분리 필요. 현재로서는 이렇게 작성하고 실제 로직 반영시에는 분리 예정
        saved.complete();

        // 현재는 HTTP 요청(Webclient), 이벤트 기반으로의 변경이 필요해보임
        reservationClient.confirmPayment(saved.getId());

        return saved.getId();
    }

    @Transactional
    public void refundPayment(Long paymentId) {
        Payment payment = paymentRepository.findById(paymentId)
            .orElseThrow(() -> new IllegalArgumentException("결제를 찾을 수 없습니다"));

        for (MileagePaymentItem item : payment.getMileagePaymentItems()) {
            restoreMileage(item);
        }

        for (CardPaymentItem item : payment.getCardPaymentItems()) {
            refundCard(item);
        }

        payment.refund();

        paymentRepository.save(payment);
    }

    private boolean useMileage(PaymentRequestCommand command) {
        return command.mileageUseAmount() != null && command.mileageUseAmount().compareTo(BigDecimal.ZERO) > 0;
    }

    private void processMileagePayment(Payment payment, PaymentRequestCommand command) {
        CustomerId customerId = CustomerId.of(command.userId());
        MileageWallet mileageWallet = mileageWalletRepository.findByCustomerId(customerId)
            .orElseThrow(() -> new IllegalArgumentException("마일리지 지갑을 찾을 수 없습니다"));


        if (!mileageWallet.isOwnedBy(command.userId())) {
            throw new IllegalArgumentException("본인의 마일리지만 사용할 수 있습니다");
        }

        mileageWallet.deduct(command.mileageUseAmount());

        payment.useMileage(
            PaymentItemAmount.of(command.mileageUseAmount()),
            mileageWallet.getId()
        );
    }

    private void restoreMileage(MileagePaymentItem item) {
        MileageWallet wallet = mileageWalletRepository.findById(item.getMileageWalletId())
            .orElseThrow(() -> new IllegalArgumentException("마일리지 지갑을 찾을 수 없습니다"));

        wallet.earn(item.getAmount().getAmount());
    }

    private void refundCard(CardPaymentItem item) {
        // TODO: 실제 PG사 환불 API 연동
    }
}
