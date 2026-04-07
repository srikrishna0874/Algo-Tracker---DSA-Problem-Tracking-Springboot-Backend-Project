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
import org.springframework.http.HttpStatusCode;
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
        try {
            User user = userService.postUser(userRequestDto);
            UserResponseDto userResponseDto = userMapper.toDto(user);
            return ResponseEntity.status(HttpStatusCode.valueOf(201))
                    .body(new ApiResponse<>(true, "User created successfully", userResponseDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>(false, e.getMessage(), null));
        }


    }

    @GetMapping("/{userId}")
    ResponseEntity<ApiResponse<UserResponseDto>> getUser(@PathVariable int userId) {
        try {
            User user = userService.getUser(userId);
            UserResponseDto userResponseDto = userMapper.toDto(user);

            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponse<UserResponseDto>(true, "User fetched successfully", userResponseDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>(false, e.getMessage(), null));
        }

    }

    @GetMapping
    ResponseEntity<ApiResponse<List<UserResponseDto>>> getAllUsers() {
        try {
            List<User> userList = userService.getAllUsers();
            List<UserResponseDto> userResponseDtoList = userList.stream().map(userMapper::toDto).toList();

            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponse<List<UserResponseDto>>(true, "Users fetched successfully",
                            userResponseDtoList));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(false, e.getMessage(), null));
        }
    }

    @DeleteMapping("/{userId}")
    ResponseEntity<ApiResponse<String>> deleteUser(@PathVariable int userId) {
        try {
            userService.deleteUser(userId);
            return ResponseEntity.ok(new ApiResponse<>(true, null, "User deleted successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>(false, e.getMessage(), null));
        }
    }

}
