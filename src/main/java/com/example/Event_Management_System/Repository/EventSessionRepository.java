package com.example.Event_Management_System.Repository;

import com.example.Event_Management_System.Modal.EventSession;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EventSessionRepository extends JpaRepository<EventSession, Long> {
    List<EventSession> findByEventIdOrderByStartTime(Long eventId);
}

