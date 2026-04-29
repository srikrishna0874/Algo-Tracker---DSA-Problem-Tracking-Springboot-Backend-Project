package com.algotracker.AlgotrackerProject.controller;

import com.algotracker.AlgotrackerProject.common.ApiResponse;
import com.algotracker.AlgotrackerProject.common.PageResponse;
import com.algotracker.AlgotrackerProject.dto.ProblemRequestDto;
import com.algotracker.AlgotrackerProject.dto.ProblemResponseDto;
import com.algotracker.AlgotrackerProject.dto.UsersWhoSolvedProblemResponseDto;
import com.algotracker.AlgotrackerProject.mapper.ProblemMapper;
import com.algotracker.AlgotrackerProject.mapper.UserProblemMapper;
import com.algotracker.AlgotrackerProject.model.Difficulty;
import com.algotracker.AlgotrackerProject.model.Problem;
import com.algotracker.AlgotrackerProject.model.UserProblem;
import com.algotracker.AlgotrackerProject.service.ProblemService;
import com.algotracker.AlgotrackerProject.service.UserProblemService;
import jakarta.validation.Valid;
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

    @Autowired
    private ProblemService problemService;

    @Autowired
    ProblemMapper problemMapper;

    @PostMapping
    public ResponseEntity<ApiResponse<ProblemResponseDto>> postProblem(
            @RequestBody @Valid ProblemRequestDto problemRequestDto) {
        Problem problem = problemService.postProblem(problemRequestDto);

        ProblemResponseDto problemResponseDto = problemMapper.toDto(problem);

        ApiResponse<ProblemResponseDto> apiResponse =
                new ApiResponse<>(true, "Problem created successfully", problemResponseDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);

    }

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<ProblemResponseDto>>> getAllProblems(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) Difficulty difficulty,
            @RequestParam(required = false) Long topicId,
            @RequestParam(required = false) String sort
    ) {
        Page<Problem> problemPage = problemService.getProblems(page, size, sort);

        List<ProblemResponseDto> problemResponseDtoList = problemPage.getContent().stream()
                .map((problem -> problemMapper.toDto(problem)))
                .toList();

        PageResponse<ProblemResponseDto> pageResponse =
                new PageResponse<>(problemResponseDtoList, problemPage.getNumber(), problemPage.getSize(),
                        problemPage.getTotalElements(), problemPage.getTotalPages());

        ApiResponse<PageResponse<ProblemResponseDto>> apiResponse =
                new ApiResponse<>(true, "Problems found", pageResponse);

        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @GetMapping("/{problemId}")
    public ResponseEntity<ApiResponse<ProblemResponseDto>> getProblemById(@PathVariable @Min(1) Long problemId) {
        Problem problem = problemService.getProblemById(problemId);
        ProblemResponseDto problemResponseDto = problemMapper.toDto(problem);

        ApiResponse<ProblemResponseDto> problemResponseDtoApiResponse =
                new ApiResponse<>(true, "Problem found", problemResponseDto);

        return ResponseEntity.status(HttpStatus.OK).body(problemResponseDtoApiResponse);
    }

    @PutMapping("/{problemId}")
    public ResponseEntity<ApiResponse<ProblemResponseDto>> updateProblem(@PathVariable @Min(1) Long problemId,
                                                                         @RequestBody
                                                                         ProblemRequestDto problemRequestDto) {
        Problem problem = problemService.updateProblem(problemId, problemRequestDto);
        ProblemResponseDto problemResponseDto = problemMapper.toDto(problem);

        ApiResponse<ProblemResponseDto> apiResponse =
                new ApiResponse<>(true, "Problem updated", problemResponseDto);

        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @DeleteMapping("/problemId")
    ResponseEntity<ApiResponse<Void>> deleteProblem(@PathVariable @Min(1) Long problemId) {
        problemService.deleteProblemById(problemId);

        ApiResponse<Void> apiResponse = new ApiResponse<>(true, "Problem deleted successfully", null);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(apiResponse);
    }

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
