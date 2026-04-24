package com.algotracker.AlgotrackerProject.controller;

import com.algotracker.AlgotrackerProject.common.ApiResponse;
import com.algotracker.AlgotrackerProject.common.PageResponse;
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
import org.springframework.data.domain.Page;
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
                .body(new ApiResponse<>(true, "User fetched successfully", userResponseDto));


    }

    @GetMapping
    ResponseEntity<ApiResponse<List<UserResponseDto>>> getAllUsers() {

        List<User> userList = userService.getAllUsers();
        List<UserResponseDto> userResponseDtoList = userList.stream().map(userMapper::toDto).toList();

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(true, "Users fetched successfully", userResponseDtoList));

    }

    @DeleteMapping("/{userId}")
    ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable @Min(1) Long userId) {

        userService.deleteUser(userId);
        return ResponseEntity.ok(new ApiResponse<>(true, "User deleted successfully", null));

    }

    @PutMapping("/{userId}")
    ResponseEntity<ApiResponse<String>> updateUser(@PathVariable @Min(1) Long userId,
                                                   @Valid @RequestBody UserUpdateRequestDto userUpdateRequestDto) {
        userService.updateUser(userId, userUpdateRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(true, "User updated successfully", null));
    }

    @GetMapping("/{userId}/problems")
    ResponseEntity<ApiResponse<PageResponse<UserProblemSolvedResponseDto>>> getProblemsByUser(
            @PathVariable @Min(1) Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<UserProblem> problemList = userProblemService.getProblemsByUser(userId, page, size);


        List<UserProblemSolvedResponseDto> userProblemSolvedResponseDtoList =
                problemList.getContent().stream()
                        .map((userProblem -> userProblemMapper.toUserProblemSolvedResponseDto(userProblem)))
                        .toList();

        PageResponse<UserProblemSolvedResponseDto> pageResponse =
                new PageResponse<>(userProblemSolvedResponseDtoList, problemList.getNumber(), problemList.getSize(),
                        problemList.getTotalElements(), problemList.getTotalPages());


        ApiResponse<PageResponse<UserProblemSolvedResponseDto>> apiResponse =
                new ApiResponse<>(true, "Problems solved by the user fetched successfully",
                        pageResponse);

        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);

    }

}
