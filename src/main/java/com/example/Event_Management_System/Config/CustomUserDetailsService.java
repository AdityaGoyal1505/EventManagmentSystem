//package com.example.Event_Management_System.Config;
//
//import com.example.Event_Management_System.Modal.User;
//import com.example.Event_Management_System.Repository.UserRepository;
//import org.springframework.security.core.userdetails.*;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//
//    private final UserRepository userRepository;
//
//    public CustomUserDetailsService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//
//        User user = userRepository.findByEmail(email)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found " + email));
//
//        // User has only ONE Role
//        List<SimpleGrantedAuthority> authorities =
//                List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().getName()));
//
//        return new org.springframework.security.core.userdetails.User(
//                user.getEmail(),
//                user.getPassword(),
//                authorities
//        );
//    }
//
//    public UserDetails loadUserById(Long id) {
//
//        User user = userRepository.findById(id)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found ID " + id));
//
//        List<SimpleGrantedAuthority> authorities =
//                List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().getName()));
//
//        return new org.springframework.security.core.userdetails.User(
//                user.getEmail(),
//                user.getPassword(),
//                authorities
//        );
//    }
//}
