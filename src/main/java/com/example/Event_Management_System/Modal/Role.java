package com.example.Event_Management_System.Modal;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "roles")
@Data
@AllArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;  // ROLE_USER, ROLE_ADMIN, etc.

    public Role() {}

    // --- Getters and Setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

//    @ManyToMany
//    @JoinTable(
//            name = "role_permissions",
//            joinColumns = @JoinColumn(name = "role_id"),
//            inverseJoinColumns = @JoinColumn(name = "permission_id")
//    )
//    private Set<Permission> permissions = new HashSet<>();
}
