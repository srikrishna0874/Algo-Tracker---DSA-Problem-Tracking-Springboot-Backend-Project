package com.algotracker.AlgotrackerProject.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TopicRequestDto {
    @NotNull
    private String topicName;
    
    private String topicDescription;
}
