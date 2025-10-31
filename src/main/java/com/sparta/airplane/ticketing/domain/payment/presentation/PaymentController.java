package com.sparta.airplane.ticketing.domain.payment.presentation;

import com.sparta.airplane.ticketing.domain.payment.application.PaymentService;
import com.sparta.airplane.ticketing.domain.payment.application.command.PaymentRequestCommand;
import com.sparta.airplane.ticketing.domain.payment.presentation.request.PaymentRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    /**
     * 결제 요청 및 승인
     * 실제로는 PG사 연동을 통해 요청과 승인이 별도로 이루어져야 하지만, 현재 단계에서는 요청 시 즉시 승인되도록 처리
     */
    @PostMapping
    public ResponseEntity<?> requestPayment(@Valid @RequestBody PaymentRequest request) {
        PaymentRequestCommand command = request.toCommand();

        Long paymentId = paymentService.requestPayment(command);

        return ResponseEntity.ok().body(paymentId);
    }

    @PostMapping("/{paymentId}/refund")
    public ResponseEntity<?> refundPayment(
        @PathVariable Long paymentId
    ) {
        paymentService.refundPayment(paymentId);

        return ResponseEntity.ok().build();
    }
}
