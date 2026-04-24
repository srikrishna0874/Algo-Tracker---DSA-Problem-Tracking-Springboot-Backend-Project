package com.algotracker.AlgotrackerProject.controller;

import com.algotracker.AlgotrackerProject.common.ApiResponse;
import com.algotracker.AlgotrackerProject.dto.UserProblemRequestDto;
import com.algotracker.AlgotrackerProject.dto.UserProblemResponseDto;
import com.algotracker.AlgotrackerProject.mapper.UserProblemMapper;
import com.algotracker.AlgotrackerProject.model.UserProblem;
import com.algotracker.AlgotrackerProject.service.UserProblemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user-problems")
public class UserProblemController {

    @Autowired
    UserProblemService userProblemService;

    @Autowired
    UserProblemMapper userProblemMapper;

    @PostMapping
    ResponseEntity<ApiResponse<UserProblemResponseDto>> postUserProblem(
            @Valid @RequestBody UserProblemRequestDto userProblemRequestDto) {
        UserProblem userProblem = userProblemService.postUserProblem(userProblemRequestDto);

        UserProblemResponseDto userProblemResponseDto = userProblemMapper.toUserProblemResponseDto(userProblem);
        ApiResponse<UserProblemResponseDto> apiResponse =
                new ApiResponse<>(true, "User Problem Created", userProblemResponseDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }
}
