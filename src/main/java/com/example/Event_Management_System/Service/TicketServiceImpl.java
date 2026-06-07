package com.example.Event_Management_System.Service;

import com.example.Event_Management_System.DTO.CreateTicketRequest;
import com.example.Event_Management_System.DTO.TicketResponse;
//import com.example.Event_Management_System.DTO.TicketMapper;
import com.example.Event_Management_System.Modal.Event;
import com.example.Event_Management_System.Modal.Payment;
import com.example.Event_Management_System.Modal.Ticket;
import com.example.Event_Management_System.Modal.User;
import com.example.Event_Management_System.Repository.EventRepository;
import com.example.Event_Management_System.Repository.PaymentRepository;
import com.example.Event_Management_System.Repository.TicketRepository;
import com.example.Event_Management_System.Repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {
    @Autowired
    private TicketRepository ticketRepository;
//    private final TicketMapper ticketMapper; // inject the mapper
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PaymentRepository paymentRepository;


//    @Override
//    public TicketResponse createTicket(CreateTicketRequest request) {
//        Event event = eventRepository.findById(request.getEventId())
//                .orElseThrow(() -> new RuntimeException("Event not found"));
//        if (event.getSeatsLeft() < request.getQuantityAvailable()) {
//            throw new RuntimeException("Only "+event.getSeatsLeft()+" Seats available");
//        }
//        if (event.getLastDate().isBefore(LocalDateTime.now())) {
//            throw new RuntimeException("The last date for to register is over");
//        }
//        event.setSeatsLeft(event.getMaxAttendees() - request.getQuantityAvailable());
//
//        int oldprice = event.getAmountPerTicket();
//        if(event.getSeatsLeft()<=(event.getMaxAttendees()/2)){
//            int increasedprice = (int) ((int) oldprice*1.25);
//
//            event.setAmountPerTicket(increasedprice);
//        }else if (event.getSeatsLeft()<=(event.getMaxAttendees()/4)){
//            int increasedprice = (int)  ((int) oldprice*1.5);
//            event.setAmountPerTicket(increasedprice);
//        }
//        eventRepository.save(event);
//
//        int price=event.getAmountPerTicket()*request.getQuantityAvailable();
//        Ticket ticket = new Ticket();
//        ticket.setType(request.getType());
//        ticket.setPrice(price);
//        ticket.setQuantityAvailable(request.getQuantityAvailable());
//        ticket.setEvent(eventRepository.findById(request.getEventId())
//                .orElseThrow(() -> new RuntimeException("Event not found")));
//        User user = userRepository.findById(request.getUserId())
//                .orElseThrow(() -> new RuntimeException("User not found"));
//        ticket.setUser(user);
//
//        Ticket tickets = ticketRepository.save(ticket);
//        Payment payment = new Payment();
//        payment.setUser(user);
//        payment.setEvent(event);
//        payment.setTicket(ticket);
//        payment.setAmount(ticket.getPrice());
//        payment.setStatus(Payment.PaymentStatus.PENDING);
//        payment.setSource(Payment.PaymentSource.SYSTEM);
//        paymentRepository.save(payment);
//
//        TicketResponse res = new TicketResponse();
//        res.setId(tickets.getId());
//        res.setPrice(tickets.getPrice());
//        res.setType(tickets.getType());
//        res.setQuantityAvailable(tickets.getQuantityAvailable());
//        res.setEventId(tickets.getEvent().getId());
//        res.setUserId(tickets.getUser().getId());
//        return  res;
//
//    }

    @Override
    public TicketResponse createTicket(
            CreateTicketRequest request,
            User user
    ) {
        Event event = eventRepository.findById(request.getEventId())
                .orElseThrow(() -> new RuntimeException("Event not found"));

        if (ticketRepository.existsByUserAndEvent(user, event)) {
            throw new RuntimeException(
                    "User has already booked a ticket for this event"
            );
        }

        if (event.getSeatsLeft() < request.getQuantityAvailable()) {
            throw new RuntimeException(
                    "Only " + event.getSeatsLeft() + " Seats available"
            );
        }

        if (event.getLastDate().isBefore(LocalDateTime.now())) {
            throw new RuntimeException(
                    "The last date to register is over"
            );
        }

        // update seats
        event.setSeatsLeft(
                event.getSeatsLeft() - request.getQuantityAvailable()
        );

        int basePrice = event.getAmountPerTicket();

        if (event.getSeatsLeft() <= (event.getMaxAttendees() / 4)) {
            event.setAmountPerTicket((int) (basePrice * 1.5));
        } else if (event.getSeatsLeft() <= (event.getMaxAttendees() / 2)) {
            event.setAmountPerTicket((int) (basePrice * 1.25));
        }

        eventRepository.save(event);

        int price = event.getAmountPerTicket()
                * request.getQuantityAvailable();

        Ticket ticket = new Ticket();
        ticket.setType(request.getType());
        ticket.setPrice(price);
        ticket.setQuantityAvailable(request.getQuantityAvailable());
        ticket.setEvent(event);
        ticket.setUser(user);

        Ticket savedTicket = ticketRepository.save(ticket);

        Payment payment = new Payment();
        payment.setUser(user);
        payment.setEvent(event);
        payment.setTicket(savedTicket);
        payment.setAmount(savedTicket.getPrice());
        payment.setStatus(Payment.PaymentStatus.PENDING);
        payment.setSource(Payment.PaymentSource.SYSTEM);

        paymentRepository.save(payment);

        TicketResponse res = new TicketResponse();
        res.setId(savedTicket.getId());
        res.setPrice(savedTicket.getPrice());
        res.setType(savedTicket.getType());
        res.setQuantityAvailable(savedTicket.getQuantityAvailable());
        res.setEventId(event.getId());
        res.setUserId(user.getId()); // allowed in response

        return res;
    }

    @Override
    public Ticket updateTicket(Long id, Ticket ticket) {
        Ticket existing = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));
        existing.setType(ticket.getType());
        existing.setPrice(ticket.getPrice());
        existing.setQuantityAvailable(ticket.getQuantityAvailable());
        return ticketRepository.save(existing);
    }

    @Override
    public Ticket getTicketById(Long id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));
    }

    @Override
    public List<Ticket> getTicketsByEvent(Long eventId) {
        return ticketRepository.findByEventId(eventId);
    }

    @Override
    public List<Ticket> getTicketsByUser(Long userId) {
        return ticketRepository.findByUserId(userId);
    }

    @Transactional
    @Override
    public void deleteTicket(Long id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        paymentRepository.deleteByTicket_Id(id); // 👈 important

        Event event = ticket.getEvent();
        if (event != null) {
            event.setSeatsLeft(event.getSeatsLeft() + ticket.getQuantityAvailable());
            eventRepository.save(event);
        }

        ticketRepository.delete(ticket);
    }


    @Override
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }
}
