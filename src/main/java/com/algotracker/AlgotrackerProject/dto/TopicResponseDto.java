package com.algotracker.AlgotrackerProject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TopicResponseDto {
    private String topicName;
    private String topicDescription;
}
