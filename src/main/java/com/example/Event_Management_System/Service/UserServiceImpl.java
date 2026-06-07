package com.example.Event_Management_System.Service;

import com.example.Event_Management_System.DTO.*;
import com.example.Event_Management_System.Exception.ResourceNotFoundException;
import com.example.Event_Management_System.Modal.Role;
import com.example.Event_Management_System.Modal.User;
import com.example.Event_Management_System.Repository.RoleRepository;
import com.example.Event_Management_System.Repository.UserRepository;
import com.example.Event_Management_System.Security.JwtUtil;
import jakarta.websocket.OnClose;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
//    private final UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private JwtUtil jwtUtil;

    private UserResponse ReturnRes(User user){
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setUsername(user.getUsername());
        response.setPhoneNo(user.getPhoneNo());
        response.setRole(user.getRole().getName());

        return response;
    }

    @Override
    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id " + id));
        return ReturnRes(user);
    }

//    @Override
//    public UserResponse getUserByName(String name) {
//        User user = userRepository.findByUsername(name)
//                .orElseThrow(() ->
//                        new ResourceNotFoundException("User not found with name " + name));
//
//        return ReturnRes(user);
//    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::ReturnRes)
                .toList();
    }


    @Override
    public UserResponse createUser(CreateUserRequest request) {
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
//        user.setPassword(request.getPassword());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setUsername(request.getUsername());
        user.setPhoneNo(request.getPhoneNo());
        // Fetch role from database
        Role role = roleRepository.findByName(request.getRole())
                .orElseThrow(() -> new ResourceNotFoundException("Role not found"));
        user.setRole(role);

        User savedUser = userRepository.save(user);
        return ReturnRes(savedUser);
    }

    @Override
    public UserResponse getUserByUsername(String username){
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return ReturnRes(user);
    }

    @Override
    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String stored = user.getPassword();
        String raw = request.getPassword();

        boolean matches;

        if (stored.startsWith("$2a$") || stored.startsWith("$2b$")) {
            matches = passwordEncoder.matches(raw, stored);
        } else {
            matches = stored.equals(raw);
            if (matches) {
                user.setPassword(passwordEncoder.encode(raw));
                userRepository.save(user);
            }
        }

        if (!matches) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(user);

        LoginResponse res = new LoginResponse();
        res.setSuccess(true);
        res.setMessage("Login successful");
        res.setToken(token);      // 🔴 IMPORTANT
        res.setRole(user.getRole().getName());
        res.setName(user.getName());

        return res;
    }



    @Override
    public void changePassword(Long userId, ChangePasswordRequest request) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 1️⃣ Verify current password (PLAINTEXT)
        if (!user.getPassword().equals(request.getCurrentPassword())) {
            throw new RuntimeException("Current password is incorrect");
        }

        // 2️⃣ New & confirm must match
        if (!request.getNewPassword().equals(request.getConfirmPassword())) {
            throw new RuntimeException("New password and confirm password do not match");
        }

        // 3️⃣ Prevent reuse of same password
        if (user.getPassword().equals(request.getNewPassword())) {
            throw new RuntimeException("New password must be different from old password");
        }

        // 4️⃣ Save new password (PLAINTEXT)
        user.setPassword(request.getNewPassword());
        userRepository.save(user);
    }

    public UserResponse updateUser(Long id, UpdateUserRequest request) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPhoneNo(request.getPhoneNo());

        if (request.getRole() != null) {
            String roleName = request.getRole();
            Role role = roleRepository.findByName(roleName)
                    .orElseThrow(() ->
                            new ResourceNotFoundException("Role not found: " + roleName));
        }

        userRepository.save(user);

        return ReturnRes(user);
    }


    @Override
    public void deleteUser(Long id) {
        User existing = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id " + id));

        userRepository.delete(existing);
    }
}
