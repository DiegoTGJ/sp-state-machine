package pdtg.sptatemachine.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import pdtg.sptatemachine.domain.Payment;
import pdtg.sptatemachine.repository.PaymentRepository;

import java.math.BigDecimal;


@SpringBootTest
class PaymentServiceImplTest {

    @Autowired
    PaymentService paymentService;

    @Autowired
    PaymentRepository paymentRepository;
    Payment payment;
    @BeforeEach
    void setUp() {
    payment = Payment.builder()
            .amount(new BigDecimal("12.99")).build();
    }

    @Transactional
    @Test
    void preAuth() {
        Payment savedPayment = paymentService.newPayment(payment);

        paymentService.preAuth(savedPayment.getId());

        Payment preAuthPayment = paymentRepository.getReferenceById(savedPayment.getId());

        System.out.println(preAuthPayment);
    }
}