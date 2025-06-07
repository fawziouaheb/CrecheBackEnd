package projet.creche.mapper;

import projet.creche.configs.enums.PaymentStatus;
import projet.creche.dto.PaymentDto;
import projet.creche.model.Payment;
import projet.creche.model.Parent;


public class PaymentMapper {

    public static PaymentDto mappeEntityToDto(Payment payment) {
        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setId(payment.getId());
        paymentDto.setAmount(payment.getAmount());
        paymentDto.setDateTransfer(payment.getDateTransfer());
        paymentDto.setMonth(payment.getMonth());
        paymentDto.setYear(payment.getYear());
        paymentDto.setCreatedAt(payment.getCreatedAt());
        paymentDto.setStatus(payment.getStatus());
        paymentDto.setInvoiceUrl(payment.getInvoiceUrl());
        paymentDto.setComment(payment.getComment());

        if (payment.getParent() != null) {
            paymentDto.setParentId(payment.getParent().getId());
            paymentDto.setParent(payment.getParent());
        }

        return paymentDto;
    }

    public static Payment mappeDtoToEntity(PaymentDto paymentDto) {
        Payment payment = new Payment();
        payment.setId(paymentDto.getId());
        payment.setAmount(paymentDto.getAmount());
        payment.setDateTransfer(paymentDto.getDateTransfer());
        payment.setMonth(paymentDto.getMonth());
        payment.setYear(paymentDto.getYear());
        payment.setStatus(paymentDto.getStatus() != null ? paymentDto.getStatus() : PaymentStatus.EN_ATTENTE);
        payment.setInvoiceUrl(paymentDto.getInvoiceUrl());
        payment.setComment(paymentDto.getComment());

        if (paymentDto.getParent() != null) {
            payment.setParent(paymentDto.getParent());
        } else if (paymentDto.getParentId() != null) {
            Parent parent = new Parent();
            parent.setId(paymentDto.getParentId());
            payment.setParent(parent); // pour mapper uniquement l'ID si nécessaire
        }

        return payment;
    }
}
