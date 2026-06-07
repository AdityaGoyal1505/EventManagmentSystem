package com.example.Event_Management_System.Service;

import com.example.Event_Management_System.DTO.CreateTicketRequest;
import com.example.Event_Management_System.DTO.TicketResponse;
import com.example.Event_Management_System.Modal.Ticket;
import com.example.Event_Management_System.Modal.User;

import java.util.List;

public interface TicketService {
    TicketResponse createTicket(CreateTicketRequest request, User user);
    Ticket updateTicket(Long id, Ticket ticket);
    void deleteTicket(Long id);
    Ticket getTicketById(Long id);
    List<Ticket> getTicketsByEvent(Long eventId);
    List<Ticket> getTicketsByUser(Long userId);
    List<Ticket> getAllTickets();
}
