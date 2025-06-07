package projet.creche.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projet.creche.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
