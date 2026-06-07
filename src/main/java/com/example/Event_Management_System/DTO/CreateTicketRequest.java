package com.example.Event_Management_System.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class CreateTicketRequest {
    @NotBlank
    private String type;
    @Min(0)
    private double price;
    @Min(1)
    private int quantityAvailable;
    @NotNull
    private Long eventId;
    @NotNull
    private Long userId;
    private String source;

    // getters / setters

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public int getQuantityAvailable() { return quantityAvailable; }
    public void setQuantityAvailable(int quantityAvailable) { this.quantityAvailable = quantityAvailable; }
    public Long getEventId() { return eventId; }
    public void setEventId(Long eventId) { this.eventId = eventId; }
    public Long getUserId() {return userId;}
    public  void setUserId(Long userId) {this.userId=userId;}

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
