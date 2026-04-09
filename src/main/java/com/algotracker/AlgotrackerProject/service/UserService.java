package com.algotracker.AlgotrackerProject.service;


import com.algotracker.AlgotrackerProject.dto.UserRequestDto;
import com.algotracker.AlgotrackerProject.dto.UserUpdateRequestDto;
import com.algotracker.AlgotrackerProject.exceptions.UserAlreadyExistsException;
import com.algotracker.AlgotrackerProject.exceptions.UserNotFoundException;
import com.algotracker.AlgotrackerProject.mapper.UserMapper;
import com.algotracker.AlgotrackerProject.model.User;
import com.algotracker.AlgotrackerProject.repo.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public User getUser(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        user.orElseThrow(() -> new UserNotFoundException("User with id = " + userId + " not found"));

        return user.get();
    }

    public List<User> getAllUsers() {

        return userRepository.findAll();
    }

    @Transactional
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with " + userId + " not found!"));

        userRepository.delete(user);

    }


    public void updateUser(@Min(1) Long userId, UserUpdateRequestDto userUpdateRequestDto) {

    }
}
