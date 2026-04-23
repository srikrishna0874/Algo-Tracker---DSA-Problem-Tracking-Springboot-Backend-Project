package com.algotracker.AlgotrackerProject.repo;

import com.algotracker.AlgotrackerProject.model.Problem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProblemRepository extends JpaRepository<Problem, Long> {
}
