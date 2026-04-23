package com.algotracker.AlgotrackerProject.controller;

import com.algotracker.AlgotrackerProject.common.ApiResponse;
import com.algotracker.AlgotrackerProject.dto.UserProblemSolvedResponseDto;
import com.algotracker.AlgotrackerProject.dto.UserRequestDto;
import com.algotracker.AlgotrackerProject.dto.UserResponseDto;
import com.algotracker.AlgotrackerProject.dto.UserUpdateRequestDto;
import com.algotracker.AlgotrackerProject.mapper.UserMapper;
import com.algotracker.AlgotrackerProject.mapper.UserProblemMapper;
import com.algotracker.AlgotrackerProject.model.User;
import com.algotracker.AlgotrackerProject.model.UserProblem;
import com.algotracker.AlgotrackerProject.service.UserProblemService;
import com.algotracker.AlgotrackerProject.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserProblemService userProblemService;

    @Autowired
    UserProblemMapper userProblemMapper;

    @PostMapping
    ResponseEntity<ApiResponse<UserResponseDto>> postUser(@Valid @RequestBody UserRequestDto userRequestDto) {

        User user = userService.postUser(userRequestDto);
        UserResponseDto userResponseDto = userMapper.toDto(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "User created successfully", userResponseDto));


    }

    @GetMapping("/{userId}")
    ResponseEntity<ApiResponse<UserResponseDto>> getUser(@PathVariable @Min(1) Long userId) {

        User user = userService.getUser(userId);
        UserResponseDto userResponseDto = userMapper.toDto(user);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<UserResponseDto>(true, "User fetched successfully", userResponseDto));


    }

    @GetMapping
    ResponseEntity<ApiResponse<List<UserResponseDto>>> getAllUsers() {

        List<User> userList = userService.getAllUsers();
        List<UserResponseDto> userResponseDtoList = userList.stream().map(userMapper::toDto).toList();

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<List<UserResponseDto>>(true, "Users fetched successfully", userResponseDtoList));

    }

    @DeleteMapping("/{userId}")
    ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable @Min(1) Long userId) {

        userService.deleteUser(userId);
        return ResponseEntity.ok(new ApiResponse<>(true, "User deleted successfully", null));

    }

    @PutMapping("/{userId")
    ResponseEntity<ApiResponse<String>> updateUser(@PathVariable @Min(1) Long userId,
                                                   @RequestBody UserUpdateRequestDto userUpdateRequestDto) {
        userService.updateUser(userId, userUpdateRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(true, "User updated successfully", null));
    }

    @GetMapping("/{userId}/problems")
    ResponseEntity<ApiResponse<List<UserProblemSolvedResponseDto>>> getProblemsByUser(@PathVariable Long userId) {
        List<UserProblem> problemList = userProblemService.getProblemsByUser(userId);

        List<UserProblemSolvedResponseDto> userProblemSolvedResponseDtoList =
                problemList.stream().map((userProblem) -> userProblemMapper.toUserProblemSolvedResponseDto(userProblem))
                        .toList();

        ApiResponse<List<UserProblemSolvedResponseDto>> listApiResponse =
                new ApiResponse<>(true, "Problems fetched successfully", userProblemSolvedResponseDtoList);

        return ResponseEntity.status(HttpStatus.OK).body(listApiResponse);

    }

}
