package com.example.Event_Management_System.Modal;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // --- RELATIONS ---
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @OneToOne(optional = false)
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;

    // --- PAYMENT DETAILS ---
    private Double amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentMethod method;   // MANUAL, UPI, CARD (future)

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentSource source;   // ADMIN, ORGANIZER, SYSTEM

    private String referenceNote;   // txn id, screenshot ref, comment

    private Instant confirmedAt;
    private LocalDateTime lastdate;
    private Instant createdAt;


    public LocalDateTime getLastdate() {
        return lastdate;
    }

    public void setLastdate(LocalDateTime lastdate) {
        this.lastdate = lastdate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public PaymentMethod getMethod() {
        return method;
    }

    public void setMethod(PaymentMethod method) {
        this.method = method;
    }

    public PaymentSource getSource() {
        return source;
    }

    public void setSource(PaymentSource source) {
        this.source = source;
    }

    public String getReferenceNote() {
        return referenceNote;
    }

    public void setReferenceNote(String referenceNote) {
        this.referenceNote = referenceNote;
    }

    public Instant getConfirmedAt() {
        return confirmedAt;
    }

    public void setConfirmedAt(Instant confirmedAt) {
        this.confirmedAt = confirmedAt;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    @PrePersist
    void onCreate() {
        createdAt = Instant.now();
        status = PaymentStatus.PENDING;
        method = PaymentMethod.MANUAL;
    }

    public enum PaymentStatus {
        PENDING,
        CONFIRMED,
        FAILED,
        REFUNDED
    }

    public enum PaymentMethod {
        MANUAL,
        UPI,
        CARD,
        NETBANKING
    }

    public enum PaymentSource {
        ADMIN,
        ORGANIZER,
        SYSTEM
    }
}
