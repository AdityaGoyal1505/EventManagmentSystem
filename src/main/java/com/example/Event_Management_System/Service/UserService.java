package com.example.Event_Management_System.Service;

import com.example.Event_Management_System.DTO.*;
import com.example.Event_Management_System.Modal.User;

import java.util.List;

public interface UserService {
    UserResponse getUserById(Long id);
    List<UserResponse> getAllUsers();
    UserResponse createUser(CreateUserRequest user);
    UserResponse updateUser(Long id, UpdateUserRequest updatedUser);
    void deleteUser(Long id);
    void changePassword(Long userId, ChangePasswordRequest request);
//    UserResponse getUserByName(String name);
    LoginResponse login(LoginRequest request);
    UserResponse getUserByUsername(String username);
}
