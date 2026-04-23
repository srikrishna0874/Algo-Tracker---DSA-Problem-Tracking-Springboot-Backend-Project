package com.algotracker.AlgotrackerProject.mapper;

import com.algotracker.AlgotrackerProject.dto.UserProblemRequestDto;
import com.algotracker.AlgotrackerProject.dto.UserProblemResponseDto;
import com.algotracker.AlgotrackerProject.dto.UserProblemSolvedResponseDto;
import com.algotracker.AlgotrackerProject.dto.UsersWhoSolvedProblemResponseDto;
import com.algotracker.AlgotrackerProject.model.Problem;
import com.algotracker.AlgotrackerProject.model.User;
import com.algotracker.AlgotrackerProject.model.UserProblem;

public class UserProblemMapper {

    public UserProblem toEntity(UserProblemRequestDto userProblemRequestDto, User user, Problem problem) {
        UserProblem userProblem = new UserProblem();

        userProblem.setUser(user);
        userProblem.setProblem(problem);

        userProblem.setNotes(userProblemRequestDto.getNotes());
        userProblem.setStatus(userProblemRequestDto.getStatus());
        userProblem.setTimeTaken(userProblemRequestDto.getDuration());
        userProblem.setSolvedAt(userProblemRequestDto.getSolvedAt());

        return userProblem;
    }

    public UserProblemResponseDto toUserProblemResponseDto(UserProblem userProblem) {
        UserProblemResponseDto userProblemResponseDto = new UserProblemResponseDto();

        userProblemResponseDto.setUserId(userProblem.getUser().getUserId());
        userProblemResponseDto.setProblemId(userProblem.getProblem().getProblemId());
        userProblemResponseDto.setNotes(userProblem.getNotes());
        userProblemResponseDto.setStatus(userProblem.getStatus());
        userProblemResponseDto.setSolvedAt(userProblem.getSolvedAt());
        userProblemResponseDto.setUserProblemId(userProblem.getUserProblemId());
        userProblemResponseDto.setDuration(userProblem.getTimeTaken());

        return userProblemResponseDto;
    }

    public UserProblemSolvedResponseDto toUserProblemSolvedResponseDto(UserProblem userProblem) {
        UserProblemSolvedResponseDto userProblemSolvedResponseDto = new UserProblemSolvedResponseDto();

        userProblemSolvedResponseDto.setProblemId(userProblem.getProblem().getProblemId());
        userProblemSolvedResponseDto.setSolvedAt(userProblem.getSolvedAt());
        userProblemSolvedResponseDto.setDifficulty(userProblem.getProblem().getDifficulty());
        userProblemSolvedResponseDto.setTitle(userProblem.getProblem().getTitle());
        userProblemSolvedResponseDto.setStatus(userProblem.getStatus());

        return userProblemSolvedResponseDto;
    }

    public UsersWhoSolvedProblemResponseDto toUsersWhoSolvedProblemResponseDto(UserProblem userProblem) {
        UsersWhoSolvedProblemResponseDto usersWhoSolvedProblemResponseDto = new UsersWhoSolvedProblemResponseDto();

        usersWhoSolvedProblemResponseDto.setUserId(userProblem.getUser().getUserId());
        usersWhoSolvedProblemResponseDto.setUserName(userProblem.getUser().getName());
        usersWhoSolvedProblemResponseDto.setUserProblemStatus(userProblem.getStatus());
        usersWhoSolvedProblemResponseDto.setSolvedAt(userProblem.getSolvedAt());

        return usersWhoSolvedProblemResponseDto;
    }
}
