package com.algotracker.AlgotrackerProject.dto;

import com.algotracker.AlgotrackerProject.model.Difficulty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProblemRequestDto {

    @NotNull
    private String title;

    private String description;

    @NotNull
    private Difficulty difficulty;

    @NotNull
    private String link;

    @NotNull
    private List<Long> topicIds;
}
