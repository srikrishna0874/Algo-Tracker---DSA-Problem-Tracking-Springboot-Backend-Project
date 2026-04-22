package com.algotracker.AlgotrackerProject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;

@Entity(name = "user_problems")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserProblem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userProblemId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "problem_id")
    private Problem problem;

    @Enumerated(EnumType.STRING)
    private UserProblemStatus status;

    private String notes;

    private Duration timeTaken;

    private LocalDateTime solvedAt;


}
