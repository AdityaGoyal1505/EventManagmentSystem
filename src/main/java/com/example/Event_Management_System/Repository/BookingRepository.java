package com.example.Event_Management_System.Repository;

import com.example.Event_Management_System.Modal.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByUser_Id(Long userId);

    List<Booking> findByEvent_Id(Long eventId);

    List<Booking> findByStatus(String status);
}
