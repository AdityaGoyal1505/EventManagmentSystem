package com.example.Event_Management_System.Service;

import com.example.Event_Management_System.DTO.CreateEventRequest;
import com.example.Event_Management_System.DTO.UpdateEventRequest;
import com.example.Event_Management_System.DTO.EventResponse;

import java.util.List;

public interface EventManagementService {
    EventResponse createEvent(CreateEventRequest request, String username);
    List<EventResponse> getAllEvents();
    EventResponse getEventById(Long id);
    EventResponse updateEvent(Long id, UpdateEventRequest request);
    List<EventResponse> getEventsByOrganizer(String Username);
    void deleteEvent(Long id);

}
