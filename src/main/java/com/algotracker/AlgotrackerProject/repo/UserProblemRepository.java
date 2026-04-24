package com.algotracker.AlgotrackerProject.repo;

import com.algotracker.AlgotrackerProject.model.UserProblem;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProblemRepository extends JpaRepository<UserProblem, Long> {

    //https://docs.spring.io/spring-data/jpa/reference/repositories/query-methods-details.html#repositories.query-methods.query-property-expressions

    boolean existsByUser_UserIdAndProblem_ProblemId(@NotNull Long userId, @NotNull Long problemId);

    Page<UserProblem> findByUser_UserId(Long userId, Pageable pageable);

    Page<UserProblem> findByProblem_ProblemId(Long problemId, Pageable pageable);
}
