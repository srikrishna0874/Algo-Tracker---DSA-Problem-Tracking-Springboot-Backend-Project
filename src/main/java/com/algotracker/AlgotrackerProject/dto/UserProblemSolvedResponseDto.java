package com.algotracker.AlgotrackerProject.dto;

import com.algotracker.AlgotrackerProject.model.Difficulty;
import com.algotracker.AlgotrackerProject.model.UserProblemStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserProblemSolvedResponseDto {

    private Long problemId;

    private String title;

    private Difficulty difficulty;

    private UserProblemStatus status;

    private LocalDateTime solvedAt;
}
