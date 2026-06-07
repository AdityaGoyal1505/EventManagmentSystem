package com.example.Event_Management_System.Modal;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.security.AuthProvider;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull @NotBlank
    @Column(unique = true)
    private String username;
    @NotNull
    private String name;
    private String email;
    private Long phoneNo;
    @NotNull
    private String password;
    @Enumerated(EnumType.STRING)
    private AuthProvider provider;

    private boolean enabled = true;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    public User() {}

    public User(Long id, String name, String email, String password, Role role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    // --- Getters and Setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public Long getPhoneNo() { return phoneNo; }
    public void setPhoneNo(Long phoneNo) { this.phoneNo = phoneNo; }

    public AuthProvider getProvider() {
        return provider;
    }

    public void setProvider(AuthProvider provider) {
        this.provider = provider;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public enum AuthProvider {
        LOCAL,
        GOOGLE
    }

}
