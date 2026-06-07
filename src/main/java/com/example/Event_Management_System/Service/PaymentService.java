package com.example.Event_Management_System.Service;

import com.example.Event_Management_System.DTO.PaymentResponse;
import com.example.Event_Management_System.Modal.Payment;

import java.util.List;

public interface PaymentService {
    List<Payment> getPaymentsByUser(Long userId);
    List<Payment> getPaymentsByEvent(Long eventId);
    List<Payment> getPaymentsByTicket(Long ticketId);

    void deletePaymentsByUser(Long userId);
    void deletePaymentsByEvent(Long eventId);
    void deletePaymentsByTicket(Long ticketId);

    void updatePaymentStatus(Long paymentId, Payment.PaymentStatus status);
//
 //    Payment updatePaymentByTicket(Long ticketId, Payment.PaymentStatus status);

    PaymentResponse updateStatus(Long paymentId, Payment.PaymentStatus status);

    PaymentResponse updateStatusByTicketId(Long ticketId, Payment.PaymentStatus status,Payment.PaymentMethod method, String referenceNote);
}
