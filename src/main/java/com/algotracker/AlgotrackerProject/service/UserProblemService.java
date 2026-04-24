package com.algotracker.AlgotrackerProject.service;

import com.algotracker.AlgotrackerProject.dto.UserProblemRequestDto;
import com.algotracker.AlgotrackerProject.exceptions.ProblemNotFoundException;
import com.algotracker.AlgotrackerProject.exceptions.UserNotFoundException;
import com.algotracker.AlgotrackerProject.mapper.UserProblemMapper;
import com.algotracker.AlgotrackerProject.model.Problem;
import com.algotracker.AlgotrackerProject.model.User;
import com.algotracker.AlgotrackerProject.model.UserProblem;
import com.algotracker.AlgotrackerProject.repo.ProblemRepository;
import com.algotracker.AlgotrackerProject.repo.UserProblemRepository;
import com.algotracker.AlgotrackerProject.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserProblemService {

    @Autowired
    private UserProblemRepository userProblemRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProblemRepository problemRepository;

    @Autowired
    UserProblemMapper userProblemMapper;

    public UserProblem postUserProblem(UserProblemRequestDto userProblemRequestDto) {
        if (!userRepository.existsById(userProblemRequestDto.getUserId())) {
            throw new UserNotFoundException("User not found");
        }

        if (!problemRepository.existsById(userProblemRequestDto.getProblemId())) {
            throw new ProblemNotFoundException("Problem not found");
        }

//        if (userProblemRepository.existsByUser_UserIdAndProblem_ProblemId(userProblemRequestDto.getUserId(),
//                userProblemRequestDto.getProblemId())) {
//            throw new UserProblemAlreadyExists("User already submitted problem");
//        }

        User user = userRepository.findById(userProblemRequestDto.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        Problem problem = problemRepository.findById(userProblemRequestDto.getProblemId())
                .orElseThrow(() -> new ProblemNotFoundException("Problem not found"));

        UserProblem userProblem = userProblemMapper.toEntity(userProblemRequestDto, user, problem);

        return userProblemRepository.save(userProblem);
    }

    public Page<UserProblem> getProblemsByUser(Long userId, int page, int size) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException("User not found");
        }

        if (page < 0 || size <= 0) {
            throw new IllegalArgumentException("Invalid pagination params");
        }

        Pageable pageable = PageRequest.of(page, size);

        return userProblemRepository.findByUser_UserId(userId, pageable);

    }

    public Page<UserProblem> getUsersByProblem(Long problemId, int page, int size) {
        if (!problemRepository.existsById(problemId)) {
            throw new ProblemNotFoundException("Problem not found");
        }

        Pageable pageable = PageRequest.of(page, size);


        return userProblemRepository.findByProblem_ProblemId(problemId, pageable);
    }
}
