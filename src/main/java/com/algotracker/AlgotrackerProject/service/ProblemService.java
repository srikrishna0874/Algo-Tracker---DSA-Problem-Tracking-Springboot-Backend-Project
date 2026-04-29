package com.algotracker.AlgotrackerProject.service;

import com.algotracker.AlgotrackerProject.dto.ProblemRequestDto;
import com.algotracker.AlgotrackerProject.exceptions.ProblemAlreadyExistsException;
import com.algotracker.AlgotrackerProject.exceptions.TopicNotFoundException;
import com.algotracker.AlgotrackerProject.mapper.ProblemMapper;
import com.algotracker.AlgotrackerProject.model.Problem;
import com.algotracker.AlgotrackerProject.model.Topic;
import com.algotracker.AlgotrackerProject.repo.ProblemRepository;
import com.algotracker.AlgotrackerProject.repo.TopicRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProblemService {

    @Autowired
    private ProblemRepository problemRepository;

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private ProblemMapper problemMapper;

    public Problem postProblem(@Valid ProblemRequestDto problemRequestDto) {
        //TODO: Develop the business logic for this service function
        if (problemRepository.existsByTitleOrLink(problemRequestDto.getTitle(), problemRequestDto.getLink())) {
            throw new ProblemAlreadyExistsException(
                    "Problem already exists");
        }

        for (Long id : problemRequestDto.getTopicIds()) {
            if (!topicRepository.existsById(id)) {
                throw new TopicNotFoundException("Topic with id: " + id + " not found!");
            }
        }

        List<Topic> topicList = topicRepository.findAllById(problemRequestDto.getTopicIds());

        Problem problem = problemMapper.toEntity(problemRequestDto, topicList);

        return problemRepository.save(problem);

    }


    public Page<Problem> getProblems(int page, int size, String sort) {
        if (page < 0 || size <= 0) {
            throw new IllegalArgumentException("Invalid pagination params");
        }

        String[] sortParams = sort.split(",");

        if (sortParams.length != 2) {
            throw new IllegalArgumentException("Invalid sort format. Use field,direction");
        }

        String field = sortParams[0],
                direction = sortParams[1];

        boolean isDescending = direction.equalsIgnoreCase("desc");

        Sort sortObj = isDescending ? Sort.by(field).descending() : Sort.by(field).ascending();

        Pageable pageable = PageRequest.of(page, size, sortObj);

        return problemRepository.findAll(pageable);
    }
}
