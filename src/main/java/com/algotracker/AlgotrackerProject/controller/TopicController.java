package com.algotracker.AlgotrackerProject.controller;

import com.algotracker.AlgotrackerProject.common.ApiResponse;
import com.algotracker.AlgotrackerProject.dto.TopicRequestDto;
import com.algotracker.AlgotrackerProject.dto.TopicResponseDto;
import com.algotracker.AlgotrackerProject.mapper.TopicMapper;
import com.algotracker.AlgotrackerProject.model.Topic;
import com.algotracker.AlgotrackerProject.repo.TopicRepository;
import com.algotracker.AlgotrackerProject.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/topics")
public class TopicController {


    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private TopicService topicService;

    @Autowired
    private TopicMapper topicMapper;

    @PostMapping
    ResponseEntity<ApiResponse<TopicResponseDto>> postTopic(@RequestBody TopicRequestDto topicRequestDto) {

        Topic topic = topicMapper.toEntityFromTopicRequest(topicRequestDto);

        Topic savedTopic = topicService.postTopic(topic);

    }

}
