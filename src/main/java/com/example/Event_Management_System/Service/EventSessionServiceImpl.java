//package com.example.Event_Management_System.Service;
//
//
//import com.example.Event_Management_System.Modal.EventSession;
//import com.example.Event_Management_System.Repository.EventSessionRepository;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//@Transactional
//public class EventSessionServiceImpl implements EventSessionService {
//    private final EventSessionRepository repo;
//    public EventSessionServiceImpl(EventSessionRepository repo) { this.repo = repo; }
//
//    @Override public EventSession create(EventSession s) { return repo.save(s); }
//    @Override public Optional<EventSession> getById(Long id) { return repo.findById(id); }
//    @Override public List<EventSession> getByEvent(Long eventId) { return repo.findByEventIdOrderByStartTime(eventId); }
//    @Override public EventSession update(EventSession s) { return repo.save(s); }
//    @Override public void delete(Long id) { repo.deleteById(id); }
//}
//
