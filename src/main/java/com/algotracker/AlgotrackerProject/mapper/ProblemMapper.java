package com.algotracker.AlgotrackerProject.mapper;


import com.algotracker.AlgotrackerProject.dto.ProblemResponseDto;
import com.algotracker.AlgotrackerProject.dto.TopicDto;
import com.algotracker.AlgotrackerProject.model.Problem;
import com.algotracker.AlgotrackerProject.model.Topic;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProblemMapper {

    @Autowired
    TopicMapper topicMapper;

    public ProblemResponseDto toDto(Problem problem) {
        ProblemResponseDto problemResponseDto = new ProblemResponseDto();

        problemResponseDto.setProblemId(problem.getProblemId());
        problemResponseDto.setDescription(problem.getDescription());
        problemResponseDto.setDifficulty(problem.getDifficulty());
        problemResponseDto.setTitle(problem.getTitle());
        problemResponseDto.setLink(problem.getLink());
        List<Topic> topics = problem.getTopics();

        List<TopicDto> topicDtos = topics.stream().map((topic -> topicMapper.toDto(topic))).toList();
        problemResponseDto.setTopics(topicDtos);

        problemResponseDto.setCreatedAt(problem.getCreated());
        problemResponseDto.setUpdatedAt(problem.getUpdated());

        return problemResponseDto;

    }
}
