package com.example.Event_Management_System.Controller;

import com.example.Event_Management_System.DTO.CreateTicketRequest;
import com.example.Event_Management_System.DTO.TicketResponse;
import com.example.Event_Management_System.Modal.Ticket;
import com.example.Event_Management_System.Modal.User;
import com.example.Event_Management_System.Security.JwtUtil;
import com.example.Event_Management_System.Service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping
    public ResponseEntity<TicketResponse> create(
            @RequestBody CreateTicketRequest request,
            @AuthenticationPrincipal User user, Authentication authentication
    ) {
        return ResponseEntity.ok(
                ticketService.createTicket(request, user)
        );
    }


    @PutMapping("/{id}")
    public ResponseEntity<Ticket> update(
            @PathVariable Long id,
            @RequestBody Ticket ticket, Authentication authentication
    ) {
        return ResponseEntity.ok(ticketService.updateTicket(id, ticket));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> get(@PathVariable Long id,Authentication authentication) {
        return ResponseEntity.ok(ticketService.getTicketById(id));
    }

    @GetMapping("/event/{eventId}")
    public ResponseEntity<List<Ticket>> getByEvent(@PathVariable Long eventId,Authentication authentication) {
        return ResponseEntity.ok(ticketService.getTicketsByEvent(eventId));
    }

    @GetMapping("/user/me")
    public ResponseEntity<List<Ticket>> getByUser(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
//        String username = user.getUsername();
        Long userId = user.getId();
        return ResponseEntity.ok(ticketService.getTicketsByUser(userId));
    }

//    @GetMapping
//    public ResponseEntity<List<Ticket>> getAll() {
//        System.out.println(">>> getAllTickets controller HIT");
//        return ResponseEntity.ok(ticketService.getAllTickets());
//    }

//    @GetMapping("/api/tickets")
//    public ResponseEntity<?> getAll(
//            @RequestHeader("Authorization") String authHeader
//    ) {
//        String token = authHeader.substring(7); // remove Bearer
//        String role = jwtUtil.extractRole(token);
//
//        if (!"Admin".equals(role)) {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN)
//                    .body("Access denied");
//        }
//
//        return ResponseEntity.ok(ticketService.getAllTickets());
//    }

    @GetMapping
    public ResponseEntity<List<Ticket>> getTickets(Authentication authentication) {
        System.out.println("Authentication: "+authentication);
        return ResponseEntity.ok(ticketService.getAllTickets());
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id,Authentication authentication) {
        ticketService.deleteTicket(id);
        return ResponseEntity.ok("Ticket deleted successfully");
    }
}
