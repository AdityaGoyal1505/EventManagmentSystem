package com.example.Event_Management_System.Repository;

import com.example.Event_Management_System.Modal.Event;
import com.example.Event_Management_System.Modal.Ticket;
import com.example.Event_Management_System.Modal.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByEventId(Long eventId);
    boolean existsByUserAndEvent(User user, Event event);

    // OR if you need the ticket itself
    Optional<Ticket> findByUserAndEvent(User user, Event event);
    List<Ticket> findByUserId(Long userId);
}
