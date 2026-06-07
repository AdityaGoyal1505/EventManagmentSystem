package com.example.Event_Management_System.DTO;

import lombok.Data;

@Data
public class CreatePaymentRequest {
    private Long bookingId;
    private double amount;
    private String method; // UPI, CARD, NETBANKING

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
