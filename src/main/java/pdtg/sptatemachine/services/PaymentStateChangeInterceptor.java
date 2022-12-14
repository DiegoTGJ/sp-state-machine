package pdtg.sptatemachine.services;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.support.StateMachineInterceptorAdapter;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Service;
import pdtg.sptatemachine.domain.Payment;
import pdtg.sptatemachine.domain.PaymentEvent;
import pdtg.sptatemachine.domain.PaymentState;
import pdtg.sptatemachine.repository.PaymentRepository;

import java.util.Optional;

/**
 * Created by Diego T. 15-08-2022
 */
@Service
@RequiredArgsConstructor
public class PaymentStateChangeInterceptor extends StateMachineInterceptorAdapter<PaymentState, PaymentEvent> {

    private final PaymentRepository paymentRepository;


    @Override
    public void preStateChange(State<PaymentState, PaymentEvent> state, Message<PaymentEvent> message, Transition<PaymentState, PaymentEvent> transition, StateMachine<PaymentState, PaymentEvent> stateMachine, StateMachine<PaymentState, PaymentEvent> rootStateMachine) {
        Optional.ofNullable(message)
                .flatMap(msg -> Optional.ofNullable((Long) msg.getHeaders().getOrDefault(PaymentServiceImpl.PAYMENT_ID_HEADER, -1L)))
                .ifPresent(paymentId -> {
            Payment payment = paymentRepository.getReferenceById(paymentId);
            payment.setState(state.getId());
            paymentRepository.save(payment);
        });
    }
}
