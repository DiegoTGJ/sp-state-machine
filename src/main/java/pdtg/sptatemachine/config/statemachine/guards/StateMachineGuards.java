package pdtg.sptatemachine.config.statemachine.guards;

import org.springframework.statemachine.guard.Guard;
import org.springframework.stereotype.Component;
import pdtg.sptatemachine.domain.PaymentEvent;
import pdtg.sptatemachine.domain.PaymentState;
import pdtg.sptatemachine.services.PaymentServiceImpl;

/**
 * Created by Diego T. 17-08-2022
 */
@Component
public class StateMachineGuards {

    public Guard<PaymentState, PaymentEvent> paymentIdGuard(){
        return context -> context.getMessageHeader(PaymentServiceImpl.PAYMENT_ID_HEADER) != null;
    }
}
