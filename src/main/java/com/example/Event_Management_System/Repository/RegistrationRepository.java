package com.example.Event_Management_System.Repository;

import com.example.Event_Management_System.Modal.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    List<Registration> findByUser_Id(Long userId);
    List<Registration> findByEvent_Id(Long eventId);
}

