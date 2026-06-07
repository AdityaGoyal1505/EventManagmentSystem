package com.example.Event_Management_System.Repository;

import com.example.Event_Management_System.Modal.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    // 1. Find only published events (homepage)
    List<Event> findByPublishedTrue();

    // 2. Find events by organizer
    List<Event> findByOrganizer_Id(Long organizerId);
    List<Event> findByOrganizer_Username(String username);
    // 3. Find events by category
    List<Event> findByCategory_Id(Long categoryId);
    // 4. Find events by venue
    List<Event> findByVenue(String venue);

    // 5. Find events happening in a date range
    List<Event> findByStartTimeBetween(LocalDateTime start, LocalDateTime end);

    List<Event> findByPublishedTrueAndStartTimeAfter(LocalDateTime now);
    // 6. Automatically fetch upcoming events
    List<Event> findByStartTimeAfter(LocalDateTime now);

    // 7. Search events by title (case-insensitive)
    List<Event> findByTitleContainingIgnoreCase(String keyword);

    // 8. Combine search + published filter
    List<Event> findByPublishedTrueAndTitleContainingIgnoreCase(String keyword);
}


