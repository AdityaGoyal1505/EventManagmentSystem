//package com.example.Event_Management_System.Service;
//
//import com.example.Event_Management_System.Exception.ResourceNotFoundException;
//import com.example.Event_Management_System.Modal.Booking;
//import com.example.Event_Management_System.Modal.Ticket;
//import com.example.Event_Management_System.Modal.User;
//import com.example.Event_Management_System.Repository.BookingRepository;
//import com.example.Event_Management_System.Repository.TicketRepository;
//import com.example.Event_Management_System.Repository.UserRepository;
//import com.example.Event_Management_System.Service.BookingService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class BookingServiceImpl implements BookingService {
//
//    private final BookingRepository bookingRepository;
//    private final UserRepository userRepository;
//    private final TicketRepository ticketRepository;
//
//    @Override
//    public Booking createBooking(Long userId, Long ticketId, int quantity) {
//
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
//
//        Ticket ticket = ticketRepository.findById(ticketId)
//                .orElseThrow(() -> new ResourceNotFoundException("Ticket not found"));
//        ticketRepository.save(ticket);
//
//        Booking booking = new Booking();
//        booking.setUser(user);
//        booking.setQuantity(quantity);
//        booking.setBookingTime(LocalDateTime.now());
//        booking.setTotalAmount(ticket.getPrice() * quantity);
//
//        return bookingRepository.save(booking);
//    }
//
//    @Override
//    public Booking getBooking(Long bookingId) {
//        return bookingRepository.findById(bookingId)
//                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));
//    }
//
//    @Override
//    public List<Booking> getBookingsByUser(Long userId) {
//        return bookingRepository.findByUser_Id(userId);
//    }
//}
