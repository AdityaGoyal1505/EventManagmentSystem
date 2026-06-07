package com.example.Event_Management_System.Repository;

import com.example.Event_Management_System.Modal.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    boolean existsByEmail(@Email @NotBlank String email);

    Optional<User> findByUsername(String username);
}

