package com.example.Event_Management_System.Modal;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is required")
    @Size(max = 100, message = "Title must be less than 100 characters")
    private String title;

    @Lob
    private String description;

    @NotBlank(message = "Location is required")
    private String location;

    @NotNull(message = "Start time is required")
    private LocalDateTime startTime;

    @NotNull(message = "End time is required")
    private LocalDateTime endTime;

    private int AmountPerTicket;

    @Min(value = 0, message = "Max attendees must be non-negative")
    private int maxAttendees;

    private boolean published;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "organizer_id", nullable = false)
    private User organizer;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private int SeatsLeft;

    private LocalDateTime LastDate;

    private String venue;

    public Event() {}

    public Event(String title, String description, String location, LocalDateTime startTime,
                 LocalDateTime endTime, int maxAttendees, boolean published, User organizer, String venue,int AmountPerTicket,int SeatsLeft,LocalDateTime LastDate) {
        this.title = title;
        this.description = description;
        this.location = location;
        this.startTime = startTime;
        this.endTime = endTime;
        this.maxAttendees = maxAttendees;
        this.published = published;
        this.organizer = organizer;
        this.venue = venue;
        this.AmountPerTicket=AmountPerTicket;
        this.SeatsLeft=SeatsLeft;
        this.LastDate=LastDate;
    }

    // Getters and Setters


    public LocalDateTime getLastDate() {
        return LastDate;
    }

    public void setLastDate(LocalDateTime lastDate) {
        this.LastDate = lastDate;
    }

    public int getSeatsLeft() {
        return SeatsLeft;
    }

    public void setSeatsLeft(int seatsLeft) {
        this.SeatsLeft = seatsLeft;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public LocalDateTime getStartTime() { return startTime; }
    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }

    public LocalDateTime getEndTime() { return endTime; }
    public void setEndTime(LocalDateTime endTime) { this.endTime = endTime; }

    public int getMaxAttendees() { return maxAttendees; }
    public void setMaxAttendees(int maxAttendees) { this.maxAttendees = maxAttendees; }

    public boolean isPublished() { return published; }
    public void setPublished(boolean published) { this.published = published; }

    public User getOrganizer() { return organizer; }
    public void setOrganizer(User organizer) { this.organizer = organizer; }

    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }

    public String getVenue() { return venue; }
    public void setVenue(String venue) { this.venue = venue; }

    public int getAmountPerTicket() {
        return AmountPerTicket;
    }

    public void setAmountPerTicket(int amountPerTicket) {
        this.AmountPerTicket = amountPerTicket;
    }
}
