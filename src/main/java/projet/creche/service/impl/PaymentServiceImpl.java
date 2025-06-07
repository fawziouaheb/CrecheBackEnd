package projet.creche.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projet.creche.configs.enums.PaymentStatus;
import projet.creche.model.Payment;
import projet.creche.repository.PaymentRepository;
import projet.creche.service.PaymentService;
@Service
public class PaymentServiceImpl implements PaymentService {
    private PaymentRepository paymentRepository;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Payment save(Payment payment) {
        return this.paymentRepository.save(payment);
    }

    @Override
    public Payment validatePayment(Long paymentId, String comment, String invoiceUrl) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Paiement introuvable avec ID : " + paymentId));

        payment.setStatus(PaymentStatus.VALIDE);
        payment.setComment(comment);
        payment.setInvoiceUrl(invoiceUrl); // à générer via service PDF si nécessaire
        return paymentRepository.save(payment);
    }

    @Override
    public Payment rejectPayment(Long paymentId, String comment) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Paiement introuvable avec ID : " + paymentId));

        payment.setStatus(PaymentStatus.REFUSE);
        payment.setComment(comment);
        return paymentRepository.save(payment);
    }
}
