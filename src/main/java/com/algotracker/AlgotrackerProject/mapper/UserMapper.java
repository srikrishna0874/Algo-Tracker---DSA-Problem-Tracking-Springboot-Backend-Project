package com.algotracker.AlgotrackerProject.mapper;

import com.algotracker.AlgotrackerProject.dto.UserRequestDto;
import com.algotracker.AlgotrackerProject.dto.UserResponseDto;
import com.algotracker.AlgotrackerProject.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponseDto toDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();

        userResponseDto.setUserId(user.getUserId());
        userResponseDto.setName(user.getName());
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setRole(user.getRole());

        return userResponseDto;
    }

    public User toEntity(UserRequestDto userRequestDto) {
        User user = new User();

        user.setEmail(userRequestDto.getEmail());
        user.setName(userRequestDto.getName());
        user.setPassword(userRequestDto.getPassword());

        return user;
    }
}
