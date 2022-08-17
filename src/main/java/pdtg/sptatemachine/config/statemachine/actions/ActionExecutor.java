package pdtg.sptatemachine.config.statemachine.actions;

import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateContext;
import pdtg.sptatemachine.domain.PaymentEvent;
import pdtg.sptatemachine.domain.PaymentState;
import pdtg.sptatemachine.services.PaymentServiceImpl;
import reactor.core.publisher.Mono;

/**
 * Created by Diego T. 17-08-2022
 */
public class ActionExecutor {
    public static void doAction(StateContext<PaymentState, PaymentEvent> ctx, PaymentEvent event){
        ctx.getStateMachine().sendEvent(Mono.just(MessageBuilder.withPayload(event)
                .setHeader(PaymentServiceImpl.PAYMENT_ID_HEADER,ctx.getMessageHeader(PaymentServiceImpl.PAYMENT_ID_HEADER))
                .build())).subscribe();
    }
}
