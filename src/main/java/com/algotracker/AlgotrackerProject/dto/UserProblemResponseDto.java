package com.algotracker.AlgotrackerProject.dto;

import com.algotracker.AlgotrackerProject.model.UserProblemStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserProblemResponseDto {

    private Long userId;

    private Long problemId;

    private Long userProblemId;

    private String notes;

    private UserProblemStatus status;

    private LocalDateTime solvedAt;

    private Duration duration;
    
}
