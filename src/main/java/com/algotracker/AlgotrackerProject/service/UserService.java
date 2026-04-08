package com.algotracker.AlgotrackerProject.service;


import com.algotracker.AlgotrackerProject.dto.UserRequestDto;
import com.algotracker.AlgotrackerProject.exceptions.UserAlreadyExistsException;
import com.algotracker.AlgotrackerProject.mapper.UserMapper;
import com.algotracker.AlgotrackerProject.model.User;
import com.algotracker.AlgotrackerProject.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserRepository userRepository;

    public User postUser(UserRequestDto userRequestDto) {

        if (userRepository.existsByEmail(userRequestDto.getEmail()))
            throw new UserAlreadyExistsException("User with this email already exists");
        
        User newUser = userMapper.toEntity(userRequestDto);
        return userRepository.save(newUser);
    }

    public User getUser(int userId) {
        return null;
    }

    public List<User> getAllUsers() {
        return null;
    }

    public void deleteUser(int userId) {

    }
}
