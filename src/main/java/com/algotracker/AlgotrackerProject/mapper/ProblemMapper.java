package com.algotracker.AlgotrackerProject.mapper;


import com.algotracker.AlgotrackerProject.dto.ProblemRequestDto;
import com.algotracker.AlgotrackerProject.dto.ProblemResponseDto;
import com.algotracker.AlgotrackerProject.dto.TopicDto;
import com.algotracker.AlgotrackerProject.model.Problem;
import com.algotracker.AlgotrackerProject.model.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
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

    public Problem toEntity(ProblemRequestDto problemRequestDto, List<Topic> topics) {
        Problem problem = new Problem();

        problem.setTitle(problemRequestDto.getTitle());
        problem.setDescription(problemRequestDto.getDescription());
        problem.setDifficulty(problemRequestDto.getDifficulty());
        problem.setLink(problemRequestDto.getLink());
        problem.setTopics(topics);

        return problem;
    }
}
