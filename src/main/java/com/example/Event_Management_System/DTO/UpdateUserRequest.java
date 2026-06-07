package com.example.Event_Management_System.DTO;
import lombok.Data;

@Data
public class UpdateUserRequest {
    private String name;
    private String email;
    private Long phoneNo;
    private String role;
}
