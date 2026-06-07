package com.example.Event_Management_System.DTO;

import com.example.Event_Management_System.Modal.Payment;
import lombok.Data;

@Data
public class PaymentResponse {
    private Long id;

    private Long userId;
    private Long eventId;

    private double amount;
    private Payment.PaymentMethod method;
    private Payment.PaymentStatus status;
    private Payment.PaymentSource soruce;
    private String ReferenceNote;

    public String getReferenceNote() {
        return ReferenceNote;
    }

    public void setReferenceNote(String referenceNote) {
        ReferenceNote = referenceNote;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }


    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Payment.PaymentMethod getMethod() {
        return method;
    }

    public void setMethod(Payment.PaymentMethod method) {
        this.method = method;
    }

    public Payment.PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(Payment.PaymentStatus status) {
        this.status = status;
    }

    public Payment.PaymentSource getSoruce() {
        return soruce;
    }

    public void setSoruce(Payment.PaymentSource soruce) {
        this.soruce = soruce;
    }
}

