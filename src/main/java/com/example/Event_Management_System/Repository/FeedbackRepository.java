package com.example.Event_Management_System.Repository;

import com.example.Event_Management_System.Modal.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    List<Feedback> findByEvent_Id(Long eventId);
}

