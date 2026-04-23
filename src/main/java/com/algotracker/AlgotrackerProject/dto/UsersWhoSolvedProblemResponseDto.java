package com.algotracker.AlgotrackerProject.dto;

import com.algotracker.AlgotrackerProject.model.UserProblemStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsersWhoSolvedProblemResponseDto {

    private Long userId;

    private String userName;

    private UserProblemStatus userProblemStatus;

    private LocalDateTime solvedAt;
}
