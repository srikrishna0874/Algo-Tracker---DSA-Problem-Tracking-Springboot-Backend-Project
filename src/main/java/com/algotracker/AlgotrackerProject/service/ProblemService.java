package com.algotracker.AlgotrackerProject.service;

import com.algotracker.AlgotrackerProject.dto.ProblemRequestDto;
import com.algotracker.AlgotrackerProject.model.Problem;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class ProblemService {
    public Problem postProblem(@Valid ProblemRequestDto problemRequestDto) {
        return null;
    }
}
