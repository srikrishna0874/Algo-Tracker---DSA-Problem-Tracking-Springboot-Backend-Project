package com.algotracker.AlgotrackerProject.repo;

import com.algotracker.AlgotrackerProject.model.Problem;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProblemRepository extends JpaRepository<Problem, Long> {
    boolean existsByTitle(@NotNull String title);

    boolean existsByTitleOrLink(@NotNull String title, @NotNull String link);

    boolean existsByTopics_TopicId(Long topicId);

    Page<Problem> findByTopics_TopicId(Long topicId, Pageable pageable);
}
