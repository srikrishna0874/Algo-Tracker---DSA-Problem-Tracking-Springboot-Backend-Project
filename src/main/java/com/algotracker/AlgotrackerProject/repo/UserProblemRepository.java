package com.algotracker.AlgotrackerProject.repo;

import com.algotracker.AlgotrackerProject.model.UserProblem;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserProblemRepository extends JpaRepository<UserProblem, Long> {

    //https://docs.spring.io/spring-data/jpa/reference/repositories/query-methods-details.html#repositories.query-methods.query-property-expressions

    boolean existsByUser_UserIdAndProblem_ProblemId(@NotNull Long userId, @NotNull Long problemId);

    List<UserProblem> findByUser_UserId(Long userId);
}
