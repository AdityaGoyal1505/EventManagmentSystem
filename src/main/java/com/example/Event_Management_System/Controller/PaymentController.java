package com.example.Event_Management_System.Controller;

import com.example.Event_Management_System.DTO.PaymentResponse;
import com.example.Event_Management_System.DTO.PaymentStatusRequest;
import com.example.Event_Management_System.Modal.Payment;
import com.example.Event_Management_System.Service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    // -------------------------------
    // 1️⃣ GET PAYMENTS
    // -------------------------------

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Payment>> getPaymentsByUser(@PathVariable Long userId, Authentication authentication) {
        return ResponseEntity.ok(paymentService.getPaymentsByUser(userId));
    }

    @GetMapping("/event/{eventId}")
    public ResponseEntity<List<Payment>> getPaymentsByEvent(@PathVariable Long eventId, Authentication authentication) {
        return ResponseEntity.ok(paymentService.getPaymentsByEvent(eventId));
    }

    @GetMapping("/ticket/{ticketId}")
    public ResponseEntity<List<Payment>> getPaymentsByTicket(@PathVariable Long ticketId, Authentication authentication) {
        return ResponseEntity.ok(paymentService.getPaymentsByTicket(ticketId));
    }

    // -------------------------------
    // 2️⃣ DELETE PAYMENTS
    // -------------------------------

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<String> deletePaymentsByUser(@PathVariable Long userId, Authentication authentication) {
        paymentService.deletePaymentsByUser(userId);
        return ResponseEntity.ok("All payments of user " + userId + " deleted successfully");
    }

    @DeleteMapping("/event/{eventId}")
    public ResponseEntity<String> deletePaymentsByEvent(@PathVariable Long eventId, Authentication authentication) {
        paymentService.deletePaymentsByEvent(eventId);
        return ResponseEntity.ok("All payments for event " + eventId + " deleted successfully");
    }

    @DeleteMapping("/ticket/{ticketId}")
    public ResponseEntity<String> deletePaymentsByTicket(@PathVariable Long ticketId, Authentication authentication) {
        paymentService.deletePaymentsByTicket(ticketId);
        return ResponseEntity.ok("All payments for ticket " + ticketId + " deleted successfully");
    }

    // -------------------------------
    // 3️⃣ CHANGE PAYMENT STATUS
    // -------------------------------

    @PutMapping("/status/{paymentId}")
    public ResponseEntity<PaymentResponse> changeStatus(
            @PathVariable Long paymentId,
            @RequestBody PaymentStatusRequest request, Authentication authentication
    ) {
        PaymentResponse response = paymentService.updateStatus(paymentId, request.getStatus());
        return ResponseEntity.ok(response);
    }

    @PutMapping("/status/ticket/{ticketId}")
    public ResponseEntity<PaymentResponse> changeStatusByTicket(
            @PathVariable Long ticketId,
            @RequestBody PaymentStatusRequest request, Authentication authentication
    ) {
        PaymentResponse response = paymentService.updateStatusByTicketId(
                ticketId,
                request.getStatus(),
                request.getMethod(),
                request.getReferenceNote()
        );

        return ResponseEntity.ok(response);
    }


}
