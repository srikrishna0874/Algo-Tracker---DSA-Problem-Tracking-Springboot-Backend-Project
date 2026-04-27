package com.algotracker.AlgotrackerProject.dto;

import com.algotracker.AlgotrackerProject.model.Difficulty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProblemResponseDto {

    private Long problemId;

    private String title;

    private String description;

    private Difficulty difficulty;

    private String link;

    private List<TopicDto> topics;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
