package com.algotracker.AlgotrackerProject.repo;

import com.algotracker.AlgotrackerProject.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Long> {
}
