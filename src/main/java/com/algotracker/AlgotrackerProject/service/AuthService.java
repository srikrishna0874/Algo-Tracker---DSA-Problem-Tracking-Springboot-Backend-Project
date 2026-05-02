package com.algotracker.AlgotrackerProject.service;

import com.algotracker.AlgotrackerProject.dto.LoginRequestDto;
import com.algotracker.AlgotrackerProject.dto.RegisterRequestDto;
import com.algotracker.AlgotrackerProject.exceptions.InvalidCredentialsException;
import com.algotracker.AlgotrackerProject.model.User;
import com.algotracker.AlgotrackerProject.repo.UserRepository;
import com.algotracker.AlgotrackerProject.security.jwt.JwtService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    public AuthService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void register(RegisterRequestDto registerRequestDto) {
        String encodedPassword = passwordEncoder.encode(registerRequestDto.getPassword());

        User user = new User();
        user.setEmail(registerRequestDto.getEmail());
        user.setPassword(encodedPassword);
        user.setName(registerRequestDto.getName());

        System.out.println(encodedPassword);

        userRepository.save(user);
    }

    public String login(LoginRequestDto loginRequestDto) {

        User user = userRepository.findByEmail(loginRequestDto.getEmail())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid email"));
        
        boolean isPasswordMatched = passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword());

        if (!isPasswordMatched) {
            throw new InvalidCredentialsException("Invalid credentials");
        }

        return jwtService.generateToken(user.getEmail());

    }

}
