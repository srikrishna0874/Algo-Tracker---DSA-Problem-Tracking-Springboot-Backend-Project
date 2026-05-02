package com.algotracker.AlgotrackerProject.controller;

import com.algotracker.AlgotrackerProject.common.ApiResponse;
import com.algotracker.AlgotrackerProject.dto.AuthResponseDto;
import com.algotracker.AlgotrackerProject.dto.LoginRequestDto;
import com.algotracker.AlgotrackerProject.dto.RegisterRequestDto;
import com.algotracker.AlgotrackerProject.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    void register(@RequestBody RegisterRequestDto registerRequestDto) {
        authService.register(registerRequestDto);
    }

    @PostMapping("/login")
    ResponseEntity<ApiResponse<AuthResponseDto>> login(@RequestBody LoginRequestDto loginRequestDto) {
        String jwtToken = authService.login(loginRequestDto);

        AuthResponseDto authResponseDto = new AuthResponseDto(jwtToken, "Logged in successfully");

        ApiResponse<AuthResponseDto> apiResponse = new ApiResponse<>(true, "Logged in successfully", authResponseDto);

        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

}
