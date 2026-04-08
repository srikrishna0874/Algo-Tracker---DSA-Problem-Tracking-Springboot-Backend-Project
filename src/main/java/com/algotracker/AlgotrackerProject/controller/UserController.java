package com.algotracker.AlgotrackerProject.controller;

import com.algotracker.AlgotrackerProject.common.ApiResponse;
import com.algotracker.AlgotrackerProject.dto.UserRequestDto;
import com.algotracker.AlgotrackerProject.dto.UserResponseDto;
import com.algotracker.AlgotrackerProject.mapper.UserMapper;
import com.algotracker.AlgotrackerProject.model.User;
import com.algotracker.AlgotrackerProject.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;

    @PostMapping
    ResponseEntity<ApiResponse<UserResponseDto>> postUser(@Valid @RequestBody UserRequestDto userRequestDto) {

        User user = userService.postUser(userRequestDto);
        UserResponseDto userResponseDto = userMapper.toDto(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "User created successfully", userResponseDto));


    }

    @GetMapping("/{userId}")
    ResponseEntity<ApiResponse<UserResponseDto>> getUser(@PathVariable int userId) {

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
    ResponseEntity<ApiResponse<String>> deleteUser(@PathVariable int userId) {

        userService.deleteUser(userId);
        return ResponseEntity.ok(new ApiResponse<>(true, "User deleted successfully", null));

    }

}
