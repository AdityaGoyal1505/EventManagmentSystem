package com.example.Event_Management_System.Controller;

import com.example.Event_Management_System.DTO.CreateEventRequest;
import com.example.Event_Management_System.DTO.EventResponse;
import com.example.Event_Management_System.DTO.UpdateEventRequest;
import com.example.Event_Management_System.Modal.User;
import com.example.Event_Management_System.Service.EventManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {
    @Autowired
    private EventManagementService eventService;

    @PostMapping("/create")
    public EventResponse createEvent(
            @RequestBody CreateEventRequest request,
            Authentication authentication
    ) {
        if (request.getCategoryId() == null) {
            throw new IllegalArgumentException("Category ID must be provided");
        }
        User user = (User) authentication.getPrincipal();
        String username = user.getUsername();
        return eventService.createEvent(request, username);
    }


    @GetMapping
    public ResponseEntity<List<EventResponse>> getAllEvents() {
        return ResponseEntity.ok(eventService.getAllEvents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventResponse> getEvent(@PathVariable Long id) {
        return ResponseEntity.ok(eventService.getEventById(id));
    }

    @GetMapping("/organizer/me")
    public ResponseEntity<List<EventResponse>> getMyEvents(
            Authentication authentication
    ) {
        User user = (User) authentication.getPrincipal();
        String username = user.getUsername();

        return ResponseEntity.ok(
                eventService.getEventsByOrganizer(username)
        );
    }


    @PutMapping("/{id}")
    public ResponseEntity<EventResponse> updateEvent(
            @PathVariable Long id,
            @RequestBody UpdateEventRequest request
    ) {
        return ResponseEntity.ok(eventService.updateEvent(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }
}
