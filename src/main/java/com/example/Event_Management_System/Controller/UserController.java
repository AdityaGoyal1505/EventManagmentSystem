package com.example.Event_Management_System.Controller;

import com.example.Event_Management_System.DTO.*;
import com.example.Event_Management_System.Modal.User;
import com.example.Event_Management_System.Repository.UserRepository;
import com.example.Event_Management_System.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private final UserService userService;

    // ✅ Admin-only (optional later with @PreAuthorize)
    @GetMapping
    public ResponseEntity<List<UserResponse>> getUsers(Authentication authentication) {
        System.out.println("Authentication: "+authentication);
        return ResponseEntity.ok(userService.getAllUsers());
    }

    // ✅ Get CURRENT logged-in user
    @GetMapping("/me")
    public ResponseEntity<UserResponse> getCurrentUser(
            @AuthenticationPrincipal User user
    ) {
        return ResponseEntity.ok(userService.getUserById(user.getId()));
    }

    // ✅ LOGIN (returns JWT)
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @RequestBody LoginRequest request
    ) {
        return ResponseEntity.ok(userService.login(request));
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getById(Authentication authentication,@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    // ✅ REGISTER (manual)
    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody CreateUserRequest request) {
        return ResponseEntity.ok(userService.createUser(request));
    }
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(
            @PathVariable Long id,
            @RequestBody UpdateUserRequest request
    ) {
        return ResponseEntity.ok(userService.updateUser(id, request));
    }
    // ✅ CHANGE OWN PASSWORD (NO userId)
    @PutMapping("/change-password")
    public ResponseEntity<String> changePassword(
            @AuthenticationPrincipal User user,
            @RequestBody ChangePasswordRequest request
    ) {
        userService.changePassword(user.getId(), request);
        return ResponseEntity.ok("Password updated successfully");
    }

    // ✅ UPDATE OWN PROFILE
//    @GetMapping("/me")
//    public ResponseEntity<UserResponse> me(@AuthenticationPrincipal String username) {
//        return ResponseEntity.ok(userService.getUserByUsername(username));
//    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(
            @PathVariable Long userId
    ) {
        userService.deleteUser(userId);
        return ResponseEntity.ok("User deleted successfully");
    }

//    // OPTIONAL: Get all users
//    @GetMapping
//    public ResponseEntity<List<UserResponse>> getAllUsers() {
//        return ResponseEntity.ok(userService.getAllUsers());
//    }

}
