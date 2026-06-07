package com.example.Event_Management_System.Modal;

import lombok.*;
import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "event_sessions")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class EventSession {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long eventId;
    private String title;
    private String speaker;
    private Instant startTime;
    private Instant endTime;
    private String location;
    private String description;
}

