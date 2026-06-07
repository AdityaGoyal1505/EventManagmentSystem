package com.example.Event_Management_System.Service;

import com.example.Event_Management_System.DTO.PaymentResponse;
import com.example.Event_Management_System.Modal.Payment;
import com.example.Event_Management_System.Repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;
    @Override
    public List<Payment> getPaymentsByUser(Long userId) {
        return paymentRepository.findByUser_Id(userId);
    }

    @Override
    public List<Payment> getPaymentsByEvent(Long eventId) {
        return paymentRepository.findByEvent_Id(eventId);
    }

    @Override
    public List<Payment> getPaymentsByTicket(Long ticketId) {
        return paymentRepository.findByTicket_Id(ticketId);
    }

    @Override
    public void deletePaymentsByUser(Long userId) {
        paymentRepository.deleteByUser_Id(userId);
    }

    @Override
    public void deletePaymentsByEvent(Long eventId) {
        paymentRepository.deleteByEvent_Id(eventId);
    }

    @Override
    public void deletePaymentsByTicket(Long ticketId) {
        paymentRepository.deleteByTicket_Id(ticketId);
    }

    @Override
    public void updatePaymentStatus(Long paymentId, Payment.PaymentStatus status) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
        payment.setStatus(status);
        paymentRepository.save(payment);
    }

//    @Override
//    public Payment updatePaymentByTicket(Long ticketId, Payment.PaymentStatus status) {
//        Payment payment = (Payment) paymentRepository
//                .findTopByTicketIdOrderByCreatedAtDesc(ticketId)
//                .orElseThrow(() -> new RuntimeException("Payment not found"));
//
//        if (payment.getStatus() == Payment.PaymentStatus.SUCCESS) {
//            throw new IllegalStateException("Successful payment cannot be modified");
//        }
//
//        payment.setStatus(status);
//        return paymentRepository.save(payment);
//    }

    @Override
    public PaymentResponse updateStatus(Long paymentId, Payment.PaymentStatus status) {

        // 1️⃣ Fetch payment
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        // 2️⃣ Update status
        payment.setStatus(status);
//        payment.setReferenceNote(payment.getReferenceNote());
        // 3️⃣ Save updated payment
        Payment pay = paymentRepository.save(payment);

        // 4️⃣ Return response DTO
//        return paymentMapper.toResponse(payment);
        PaymentResponse res = new PaymentResponse();
        res.setId(pay.getId());
        res.setStatus(pay.getStatus());
        res.setAmount(pay.getAmount());
        res.setMethod(pay.getMethod());
        res.setEventId(pay.getEvent().getId());
        res.setUserId(pay.getUser().getId());
        return res;
    }

//    public PaymentResponse updateStatusByTicketId(Long ticketId, Payment.PaymentStatus status) {
//        Payment payment = paymentRepository.findByTicketId(ticketId);
////                .orElseThrow(() -> new RuntimeException("Payment not found for ticket"));
//        payment.setStatus(status);
//        Payment pay = paymentRepository.save(payment);
//        PaymentResponse res = new PaymentResponse();
//        res.setId(pay.getId());
//        res.setStatus(pay.getStatus());
//        res.setAmount(pay.getAmount());
//        res.setMethod(pay.getMethod());
//        res.setEventId(pay.getEvent().getId());
//        res.setUserId(pay.getUser().getId());
//        return res;
//    }

    @Override
    public PaymentResponse updateStatusByTicketId(Long ticketId, Payment.PaymentStatus status, Payment.PaymentMethod method, String referenceNote) {
        Payment payment = paymentRepository
                .findByTicketId(ticketId);
//                .orElseThrow(() -> new RuntimeException("Payment not found for ticket"));

//        if (payment.getStatus() == Payment.PaymentStatus.CONFIRMED) {
//            throw new RuntimeException("Confirmed payment cannot be modified");
//        }

        payment.setStatus(status);

        if (status == Payment.PaymentStatus.CONFIRMED && referenceNote != null) {
            payment.setMethod(method);
            payment.setReferenceNote(referenceNote);
        }

        Payment pay = paymentRepository.save(payment);
        PaymentResponse res = new PaymentResponse();
        res.setId(pay.getId());
        res.setStatus(pay.getStatus());
        res.setAmount(pay.getAmount());
        res.setMethod(pay.getMethod());
        res.setEventId(pay.getEvent().getId());
        res.setUserId(pay.getUser().getId());
        return res;
    }

}
