package com.algotracker.AlgotrackerProject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity(name = "users")
@Table(name = "users",
        schema = "public",
        comment = "This is used to store users data",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_email", columnNames = {"email"})
        },
        indexes = {
                @Index(name = "idx_email", columnList = "email"),
        }
)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "email", comment = "Used to store emails of users", nullable = false)
    private String email;

    @Column(name = "name", nullable = false, length = 100, comment = "Used to store names of users")
    private String name;

    @com.fasterxml.jackson.annotation.JsonIgnore
    @Column(name = "password", nullable = false, length = 255, comment = "Used to store passwords")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role = Role.USER;

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

}

