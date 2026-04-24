package com.algotracker.AlgotrackerProject.controller;

import com.algotracker.AlgotrackerProject.common.ApiResponse;
import com.algotracker.AlgotrackerProject.common.PageResponse;
import com.algotracker.AlgotrackerProject.dto.UsersWhoSolvedProblemResponseDto;
import com.algotracker.AlgotrackerProject.mapper.UserProblemMapper;
import com.algotracker.AlgotrackerProject.model.UserProblem;
import com.algotracker.AlgotrackerProject.service.UserProblemService;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/problems")
public class ProblemController {

    @Autowired
    private UserProblemService userProblemService;

    @Autowired
    private UserProblemMapper userProblemMapper;


    @GetMapping("/{problemId}/users")
    public ResponseEntity<ApiResponse<PageResponse<UsersWhoSolvedProblemResponseDto>>> getUsersByProblem(
            @PathVariable @Min(1) Long problemId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<UserProblem> userProblemList = userProblemService.getUsersByProblem(problemId, page, size);

        List<UsersWhoSolvedProblemResponseDto> usersWhoSolvedProblemResponseDtoList =
                userProblemList.getContent().stream()
                        .map((userProblem -> userProblemMapper.toUsersWhoSolvedProblemResponseDto(userProblem)))
                        .toList();

        PageResponse<UsersWhoSolvedProblemResponseDto> pageResponse =
                new PageResponse<>(usersWhoSolvedProblemResponseDtoList, userProblemList.getNumber(),
                        userProblemList.getSize(), userProblemList.getTotalElements(), userProblemList.getTotalPages());


        ApiResponse<PageResponse<UsersWhoSolvedProblemResponseDto>> apiResponse =
                new ApiResponse<>(true, "Users fetched successfully", pageResponse);

        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);

    }

}
