package pdtg.sptatemachine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdtg.sptatemachine.domain.Payment;

/**
 * Created by Diego T. 07-08-2022
 */
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
