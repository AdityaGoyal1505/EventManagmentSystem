package com.example.Event_Management_System.DTO;

import lombok.Data;

import java.time.LocalDateTime;

//@Data
//public class UpdateEventRequest {
//    private String title;
//    private String description;
//    private String location;
//    private LocalDateTime startTime;
//    private LocalDateTime endTime;
//    private Integer maxAttendees;
//    private Integer amountPerTicket;
//    private Boolean published;
//    private Long categoryId;
//    private Long venueId;
//
//    public void setPublished(Boolean published) {
//        this.published = published;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public String getLocation() {
//        return location;
//    }
//
//    public void setLocation(String location) {
//        this.location = location;
//    }
//
//    public LocalDateTime getStartTime() {
//        return startTime;
//    }
//
//    public void setStartTime(LocalDateTime startTime) {
//        this.startTime = startTime;
//    }
//
//    public LocalDateTime getEndTime() {
//        return endTime;
//    }
//
//    public void setEndTime(LocalDateTime endTime) {
//        this.endTime = endTime;
//    }
//
//    public Integer getMaxAttendees() {
//        return maxAttendees;
//    }
//
//    public void setMaxAttendees(Integer maxAttendees) {
//        this.maxAttendees = maxAttendees;
//    }
//
//    public Integer getAmountPerTicket() {
//        return amountPerTicket;
//    }
//
//    public void setAmountPerTicket(Integer amountPerTicket) {
//        this.amountPerTicket = amountPerTicket;
//    }
//
////    public boolean isPublished() {
////        return published;
////    }
////
////    public void setPublished(boolean published) {
////        this.published = published;
////    }
//
//    public Long getCategoryId() {
//        return categoryId;
//    }
//
//    public void setCategoryId(Long categoryId) {
//        this.categoryId = categoryId;
//    }
//
//    public Long getVenueId() {
//        return venueId;
//    }
//
//    public void setVenueId(Long venueId) {
//        this.venueId = venueId;
//    }
//
//    public Boolean getPublished() {
//        return published;
//    }
//}
//


//@Data
public class UpdateEventRequest {
    private String title;
    private String description;
    private String location;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer maxAttendees;
    private Integer amountPerTicket;
    private boolean published;
    private Long categoryId;
    private Long venueId;
    private LocalDateTime lastDate;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Integer getMaxAttendees() {
        return maxAttendees;
    }

    public void setMaxAttendees(Integer maxAttendees) {
        this.maxAttendees = maxAttendees;
    }

    public Integer getAmountPerTicket() {
        return amountPerTicket;
    }

    public void setAmountPerTicket(Integer amountPerTicket) {
        this.amountPerTicket = amountPerTicket;
    }

    public boolean getPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getVenueId() {
        return venueId;
    }

    public void setVenueId(Long venueId) {
        this.venueId = venueId;
    }

    public LocalDateTime getLastDate() {
        return lastDate;
    }

    public void setLastDate(LocalDateTime lastDate) {
        this.lastDate = lastDate;
    }
}
