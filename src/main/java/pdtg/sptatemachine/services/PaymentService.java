package pdtg.sptatemachine.services;

import org.springframework.statemachine.StateMachine;
import pdtg.sptatemachine.domain.Payment;
import pdtg.sptatemachine.domain.PaymentEvent;
import pdtg.sptatemachine.domain.PaymentState;

/**
 * Created by Diego T. 14-08-2022
 */
public interface PaymentService {
    Payment newPayment(Payment payment);

    StateMachine<PaymentState, PaymentEvent> preAuth(Long paymentId);
    StateMachine<PaymentState, PaymentEvent> authorizePayment(Long paymentId);
    StateMachine<PaymentState, PaymentEvent> declineAuth(Long paymentId);
}
