package com.algotracker.AlgotrackerProject.service;

import com.algotracker.AlgotrackerProject.dto.TopicRequestDto;
import com.algotracker.AlgotrackerProject.exceptions.TopicAlreadyExistsException;
import com.algotracker.AlgotrackerProject.exceptions.TopicInUseException;
import com.algotracker.AlgotrackerProject.exceptions.TopicNotFoundException;
import com.algotracker.AlgotrackerProject.mapper.TopicMapper;
import com.algotracker.AlgotrackerProject.model.Problem;
import com.algotracker.AlgotrackerProject.model.Topic;
import com.algotracker.AlgotrackerProject.repo.ProblemRepository;
import com.algotracker.AlgotrackerProject.repo.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class TopicService {


    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private TopicMapper topicMapper;

    @Autowired
    private ProblemRepository problemRepository;


    public Topic postTopic(TopicRequestDto topicRequestDto) {
        if (topicRepository.existsByName(topicRequestDto.getTopicName())) {
            throw new TopicAlreadyExistsException(
                    "Topic with name " + topicRequestDto.getTopicName() + " already exists");
        }

        Topic topic = topicMapper.toEntityFromTopicRequest(topicRequestDto);

        return topicRepository.save(topic);
    }


    public Page<Topic> getAllTopics(int page, int size, String sort) {
        if (page < 0 || size <= 0) {
            throw new IllegalArgumentException("Invalid pagination params");
        }

        if (sort == null || sort.isBlank()) {
            Pageable pageable = PageRequest.of(page, size);

            return topicRepository.findAll(pageable);
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

        return topicRepository.findAll(pageable);
    }

    public Topic getTopicById(long topicId) {
        return topicRepository.findById(topicId)
                .orElseThrow(() -> new TopicNotFoundException("Topic with id " + topicId + " not found"));
    }

    public Topic updateTopicById(Long topicId, TopicRequestDto topicRequestDto) {
        if (!topicRepository.existsById(topicId)) {
            throw new TopicNotFoundException("Topic with name " + topicRequestDto.getTopicName() + " not found");
        }
        Topic topic = topicMapper.toEntityFromTopicRequest(topicRequestDto);

        return topicRepository.save(topic);
    }

    public void deleteByTopicId(Long topicId) {

        if (!topicRepository.existsById(topicId)) {
            throw new TopicNotFoundException("Topic with id " + topicId + " not found");
        }
        if (problemRepository.existsByTopics_TopicId(topicId)) {
            throw new TopicInUseException("Topic with id " + topicId + " is already in use");
        }

        topicRepository.deleteById(topicId);
    }

    public Page<Problem> getProblemsByTopic(Long topicId, int page, int size) {
        if (page < 0 || size <= 0) {
            throw new IllegalArgumentException("Invalid pagination params");
        }

        if (!topicRepository.existsById(topicId)) {
            throw new TopicNotFoundException("Topic with id " + topicId + " not found");
        }

        Pageable pageable = PageRequest.of(page, size);

        return problemRepository.findByTopics_TopicId(topicId, pageable);
    }
}
