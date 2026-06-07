package com.example.Event_Management_System.Repository;

import com.example.Event_Management_System.Modal.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findByUser_Id(Long userId);
    List<Payment> findByEvent_Id(Long eventId);
    List<Payment> findByTicket_Id(Long ticketId);

    Payment findByUserId(Long userId);
    Payment findByEventId(Long eventId);
    Payment findByTicketId(Long ticketId);
    void deleteByUser_Id(Long userId);
    void deleteByEvent_Id(Long eventId);
    void deleteByTicket_Id(Long ticketId);
}
