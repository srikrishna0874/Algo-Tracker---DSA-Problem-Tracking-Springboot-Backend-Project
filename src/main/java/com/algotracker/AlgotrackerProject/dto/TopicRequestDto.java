package com.algotracker.AlgotrackerProject.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TopicRequestDto {
    @NotBlank
    private String topicName;

    private String topicDescription;
}
