//package com.example.Event_Management_System.Controller;
//
//import com.example.Event_Management_System.DTO.LoginRequest;
//import com.example.Event_Management_System.DTO.LoginResponse;
//import com.example.Event_Management_System.Service.AuthService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/auth")
//public class AuthController {
//
//    @Autowired
//    private AuthService authService;
//
//    @PostMapping("/login")
//    public ResponseEntity<LoginResponse> login(
//            @RequestBody LoginRequest request) {
//
//        return ResponseEntity.ok(userService.login(request));
//    }
//}
//
