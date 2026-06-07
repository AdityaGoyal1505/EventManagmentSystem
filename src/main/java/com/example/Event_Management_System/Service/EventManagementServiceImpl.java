package com.example.Event_Management_System.Service;

import com.example.Event_Management_System.DTO.*;
import com.example.Event_Management_System.Modal.*;
import com.example.Event_Management_System.Repository.*;
import lombok.RequiredArgsConstructor;
//import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventManagementServiceImpl implements EventManagementService {
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private VenueRepository venueRepository;
//    private final ModelMapper modelMapper;
    @Autowired
    private CategoryRepository categoryRepository;
//    private final EventMapper eventMapper;

    private EventResponse ReturnRes(Event event){
        EventResponse res = new EventResponse();
        res.setId(event.getId());
        res.setOrganizerId(event.getOrganizer().getId());
        res.setCategoryId(event.getCategory().getId());
        res.setCategoryName(event.getCategory().getName());
        res.setOrganizerName(event.getOrganizer().getName());
        res.setSeatsLeft(event.getMaxAttendees());
        res.setMaxAttendees(event.getMaxAttendees());
        res.setTitle(event.getTitle());
        res.setLocation(event.getLocation());
        res.setAmountPerTicket(event.getAmountPerTicket());
        res.setDescription(event.getDescription());
        res.setStartTime(event.getStartTime());
        res.setEndTime(event.getEndTime());
        res.setPublished(event.isPublished());
        eventRepository.save(event);
        res.setPublished(event.isPublished());
        res.setLastDate(event.getLastDate());
        return res;
    }

    @Override
    public EventResponse createEvent(CreateEventRequest request, String username) {

        User organizer = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Organizer not found"));

        Event event = new Event();
        event.setSeatsLeft(request.getMaxAttendees());
        event.setMaxAttendees(request.getMaxAttendees());
        event.setTitle(request.getTitle());
        event.setAmountPerTicket(request.getAmountPerTicket());
        event.setDescription(request.getDescription());
        event.setStartTime(request.getStartTime());
        event.setEndTime(request.getEndTime());
        event.setLocation(request.getLocation());
        event.setLastDate(request.getLastDate());
        event.setPublished(request.isPublished());
        updatePublishStatus(event);

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        event.setCategory(category);

        Event savedEvent=eventRepository.save(event);
        return ReturnRes(savedEvent);
    }

//    @Override
//    public EventResponse createEvent(CreateEventRequest request, String username) {
//        Event event = new Event();
//        User organizer = userRepository.findById(request.getOrganizerId())
//                .orElseThrow(() -> new RuntimeException("User not found"));
//        event.setOrganizer(organizer);
//
//        Category category = categoryRepository.findById(request.getCategoryId())
//                .orElseThrow(() -> new RuntimeException("Category not found"));
//        event.setCategory(category);
//
//        event.setSeatsLeft(request.getMaxAttendees());
//        event.setMaxAttendees(request.getMaxAttendees());
//        event.setTitle(request.getTitle());
//        event.setAmountPerTicket(request.getAmountPerTicket());
//        event.setDescription(request.getDescription());
//        event.setStartTime(request.getStartTime());
//        event.setEndTime(request.getEndTime());
//        event.setLocation(request.getLocation());
//        event.setLastDate(request.getLastDate());
//        event.setPublished(request.isPublished());
//        updatePublishStatus(event);
//        // Save the event
//        Event savedEvent = eventRepository.save(event);
//        return ReturnRes(savedEvent);
//    }

    @Override
    public List<EventResponse> getAllEvents() {
        return eventRepository.findAll()
                .stream()
                .map(this::ReturnRes)
                .collect(Collectors.toList());
    }

    @Override
    public EventResponse getEventById(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));
        return ReturnRes(event);
    }
//    @Override
//    public List<EventResponse> getEventsByOrganizer(Long organizerId) {
//        return eventRepository.findByOrganizer_Id(organizerId)
//                .stream()
//                .map(this::ReturnRes)
//                .collect(Collectors.toList());
//    }

    @Override
    public List<EventResponse> getEventsByOrganizer(String username) {
        List<Event> events =
                eventRepository.findByOrganizer_Username(username);
        System.out.println("Username: "+username);
        System.out.println("Events found: " + events.size());

        return events.stream()
                .map(this::ReturnRes)
                .collect(Collectors.toList());
    }

    private void updatePublishStatus(Event event) {
        LocalDate today = LocalDate.now();
        System.out.println("Update Publish has been called");
        if (event.getLastDate() != null &&
                (event.getLastDate().isEqual(today.atStartOfDay()) || event.getLastDate().isAfter(today.atStartOfDay()))) {

            event.setPublished(true);
        }
    }

    @Override
    public EventResponse updateEvent(Long id, UpdateEventRequest request) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));
        int oldMax = event.getMaxAttendees();
        int oldSeatsLeft = event.getSeatsLeft();
        event.setTitle(request.getTitle());
        Category cat = categoryRepository.findById(request.getCategoryId())
                        .orElseThrow(() -> new RuntimeException("Category not found"));
        event.setCategory(cat);
        event.setMaxAttendees(request.getMaxAttendees());
        event.setStartTime(request.getStartTime());
        event.setEndTime(request.getEndTime());
        event.setLocation(request.getLocation());
        event.setDescription(request.getDescription());
        event.setAmountPerTicket(request.getAmountPerTicket());
        event.setLastDate(request.getLastDate());
//        event.setPublished(request.getPublished());
//        if (request.getPublished() != null) {
//            event.setPublished(request.getPublished());
//        }
        int newMax = request.getMaxAttendees();
        int updatedSeatsLeft = oldSeatsLeft + (newMax - oldMax);
// prevent negative seats
        updatedSeatsLeft = Math.max(updatedSeatsLeft, 0);
        event.setSeatsLeft(updatedSeatsLeft);
        if (Boolean.TRUE.equals(request.getPublished())) {
            updatePublishStatus(event);
        } else {
            event.setPublished(false);
        }
        Event eventrep =  eventRepository.save(event);
        return ReturnRes(eventrep);
    }

    @Override
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }
}
