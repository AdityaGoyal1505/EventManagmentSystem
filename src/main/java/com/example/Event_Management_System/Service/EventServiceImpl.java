//package com.example.Event_Management_System.Service;
//
//import com.example.Event_Management_System.DTO.CreateEventRequest;
//import com.example.Event_Management_System.DTO.EventMapper;
//import com.example.Event_Management_System.DTO.EventResponse;
//import com.example.Event_Management_System.Modal.Category;
//import com.example.Event_Management_System.Modal.Event;
//import com.example.Event_Management_System.Modal.User;
//import com.example.Event_Management_System.Repository.CategoryRepository;
//import com.example.Event_Management_System.Repository.EventRepository;
//import com.example.Event_Management_System.Repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//@RequiredArgsConstructor
//public class EventServiceImpl implements EventService {
//    @Autowired
//    private EventRepository eventRepository;
//
//    private final ModelMapper modelMapper;
//    @Autowired
//    private UserRepository userRepository;
//    @Autowired
//    private CategoryRepository categoryRepository;
//
//    private final EventMapper eventMapper;
//
//
//    @Override
//    public EventResponse createEvent(CreateEventRequest request) {
//        Event event = new Event();
//        // Fetch existing organizer and category from DB
//        User organizer = userRepository.findById(request.getOrganizerId())
//                .orElseThrow(() -> new RuntimeException("User not found"));
//        event.setOrganizer(organizer);
//
//        Category category = categoryRepository.findById(request.getCategoryId())
//                .orElseThrow(() -> new RuntimeException("Category not found"));
//        event.setCategory(category);
//
//        // Save the event
//        Event savedEvent = eventRepository.save(event);
//
//        // Convert to response DTO
//        return eventMapper.toResponse(savedEvent);
//    }
//
//
//    @Override
//    public EventMapper updateEvent(Long id, EventMapper eventDTO) {
//        Event existingEvent = eventRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Event not found"));
//
//        modelMapper.map(eventDTO, existingEvent);
//
//        return modelMapper.map(eventRepository.save(existingEvent), EventMapper.class);
//    }
//
//    @Override
//    public void deleteEvent(Long id) {
//        eventRepository.deleteById(id);
//    }
//
//    @Override
//    public EventMapper getEventById(Long id) {
//        Event event = eventRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Event not found"));
//
//        return modelMapper.map(event, EventMapper.class);
//    }
//
//    @Override
//    public List<EventMapper> getAllPublishedEvents() {
//        return eventRepository.findByPublishedTrue()
//                .stream()
//                .map(event -> modelMapper.map(event, EventMapper.class))
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public List<EventMapper> getEventsByOrganizer(Long organizerId) {
//        return eventRepository.findByOrganizer_Id(organizerId)
//                .stream()
//                .map(event -> modelMapper.map(event, EventMapper.class))
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public List<EventMapper> searchEvents(String keyword) {
//        return eventRepository.findByTitleContainingIgnoreCase(keyword)
//                .stream()
//                .map(event -> modelMapper.map(event, EventMapper.class))
//                .collect(Collectors.toList());
//    }
//}
