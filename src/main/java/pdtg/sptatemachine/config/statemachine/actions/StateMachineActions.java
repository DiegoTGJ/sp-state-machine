package pdtg.sptatemachine.config.statemachine.actions;

import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;
import pdtg.sptatemachine.domain.PaymentEvent;
import pdtg.sptatemachine.domain.PaymentState;

import java.util.Random;

import static pdtg.sptatemachine.config.statemachine.actions.ActionExecutor.doAction;

/**
 * Created by Diego T. 17-08-2022
 */
@Component
public class StateMachineActions {
    public  Action<PaymentState, PaymentEvent> authAction(){
        return context -> {
            System.out.println("Auth was called!");
            if (new Random().nextInt(10)<8){
                System.out.println("Approved");
                doAction(context,PaymentEvent.AUTH_APPROVED);
            }else {
                System.out.println("Declined wrong credentials");
                doAction(context,PaymentEvent.AUTH_DECLINED);
            }
        };
    }

    public  Action<PaymentState, PaymentEvent> preAuthAction() {
        return context -> {
            System.out.println("PreAuth was called !!!");
            if(new Random().nextInt(10) < 8){
                System.out.println("Approved");
                doAction(context,PaymentEvent.PRE_AUTH_APPROVED);
            }else {
                System.out.println("Declined no credit!");
                doAction(context,PaymentEvent.PRE_AUTH_DECLINED);
            }
        };
    }
    public  Action<PaymentState, PaymentEvent> preAuthDeclinedAction() {
        return context -> System.out.println("Pre auth was declined");
    }

    public  Action<PaymentState, PaymentEvent> preAuthApproved() {
        return context -> System.out.println("Pre auth was approved");
    }
    public  Action<PaymentState, PaymentEvent> authDeclinedAction() {
        return context -> System.out.println("Auth was declined");
    }

    public  Action<PaymentState, PaymentEvent> authApprovedAction() {
        return context -> System.out.println("Auth was approved");
    }
}
