package pdtg.sptatemachine.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import pdtg.sptatemachine.domain.PaymentEvent;
import pdtg.sptatemachine.domain.PaymentState;
import reactor.core.publisher.Mono;

import java.util.UUID;


@SpringBootTest
class StateMachineConfigTest {
    @Autowired
    StateMachineFactory<PaymentState, PaymentEvent> factory;

    @Test
    void newStateMachineTest() {
        StateMachine<PaymentState, PaymentEvent> sm = factory.getStateMachine(UUID.randomUUID());

        sm.startReactively().subscribe();
        sendEvent(sm,PaymentEvent.PRE_AUTHORIZE);
        sendEvent(sm,PaymentEvent.PRE_AUTH_APPROVED);

    }

    private void sendEvent(StateMachine<PaymentState,PaymentEvent> sm,PaymentEvent paymentEvent){
        sm.sendEvent(Mono.just(MessageBuilder.withPayload(paymentEvent).build())).subscribe();
    }
}