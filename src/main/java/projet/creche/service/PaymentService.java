package projet.creche.service;

import projet.creche.model.Payment;

public interface PaymentService {
    Payment save(Payment payment);
    Payment validatePayment(Long paymentId, String comment, String invoiceUrl);
    Payment rejectPayment(Long paymentId, String comment);
}
