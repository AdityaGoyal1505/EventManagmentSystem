package com.example.Event_Management_System.DTO;

import lombok.Data;

@Data
public class LoginResponse {
    private Long id;
    private boolean success;
    private String message;
    private String role;
    private String name;
    private String username;
    private Long PhoneNo;
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getPhoneNo() {
        return PhoneNo;
    }

    public void setPhoneNo(Long phoneNo) {
        PhoneNo = phoneNo;
    }
}

