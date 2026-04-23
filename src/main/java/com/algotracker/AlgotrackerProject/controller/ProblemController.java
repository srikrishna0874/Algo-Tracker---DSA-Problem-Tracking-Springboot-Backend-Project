package com.algotracker.AlgotrackerProject.controller;

import com.algotracker.AlgotrackerProject.common.ApiResponse;
import com.algotracker.AlgotrackerProject.dto.UsersWhoSolvedProblemResponseDto;
import com.algotracker.AlgotrackerProject.mapper.UserProblemMapper;
import com.algotracker.AlgotrackerProject.model.UserProblem;
import com.algotracker.AlgotrackerProject.service.UserProblemService;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/problems")
public class ProblemController {

    @Autowired
    private UserProblemService userProblemService;

    @Autowired
    private UserProblemMapper userProblemMapper;

    @GetMapping("/{problemId}/users")
    public ResponseEntity<ApiResponse<List<UsersWhoSolvedProblemResponseDto>>> getUsersByProblem(
            @PathVariable @Min(1) Long problemId) {
        List<UserProblem> userProblemList = userProblemService.getUsersByProblem(problemId);

        List<UsersWhoSolvedProblemResponseDto> usersWhoSolvedProblemResponseDtoList = userProblemList.stream()
                .map((userProblem -> userProblemMapper.toUsersWhoSolvedProblemResponseDto(userProblem))).toList();

        ApiResponse<List<UsersWhoSolvedProblemResponseDto>> apiResponse =
                new ApiResponse<>(true, "Users fetched successfully", usersWhoSolvedProblemResponseDtoList);

        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);

    }

}
