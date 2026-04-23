package com.algotracker.AlgotrackerProject.dto;

import com.algotracker.AlgotrackerProject.model.UserProblemStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserProblemRequestDto {
    @NotNull
    private Long userId;

    @NotNull
    private Long problemId;

    @NotNull
    private UserProblemStatus status;

    private String notes;

    private Duration duration;

    private LocalDateTime solvedAt;
}
