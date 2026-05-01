package com.algotracker.AlgotrackerProject.controller;

import com.algotracker.AlgotrackerProject.common.ApiResponse;
import com.algotracker.AlgotrackerProject.common.PageResponse;
import com.algotracker.AlgotrackerProject.dto.ProblemResponseDto;
import com.algotracker.AlgotrackerProject.dto.TopicRequestDto;
import com.algotracker.AlgotrackerProject.dto.TopicResponseDto;
import com.algotracker.AlgotrackerProject.mapper.ProblemMapper;
import com.algotracker.AlgotrackerProject.mapper.TopicMapper;
import com.algotracker.AlgotrackerProject.model.Problem;
import com.algotracker.AlgotrackerProject.model.Topic;
import com.algotracker.AlgotrackerProject.service.TopicService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topics")
public class TopicController {

    @Autowired
    private TopicService topicService;

    @Autowired
    private TopicMapper topicMapper;

    @Autowired
    private ProblemMapper problemMapper;

    @PostMapping
    ResponseEntity<ApiResponse<TopicResponseDto>> postTopic(@RequestBody @Valid TopicRequestDto topicRequestDto) {

        Topic savedTopic = topicService.postTopic(topicRequestDto);

        TopicResponseDto topicResponseDto = topicMapper.toTopicResponseDto(savedTopic);

        ApiResponse<TopicResponseDto> apiResponse =
                new ApiResponse<>(true, "Topic saved successfully", topicResponseDto);

        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);

    }

    @GetMapping
    ResponseEntity<ApiResponse<PageResponse<TopicResponseDto>>> getAllTopics(@RequestParam(defaultValue = "0") int page,
                                                                             @RequestParam(defaultValue = "10")
                                                                             int size,
                                                                             @RequestParam(required = false)
                                                                             String sort) {
        Page<Topic> topicPage = topicService.getAllTopics(page, size, sort);

        List<TopicResponseDto> topicResponseDtoList =
                topicPage.getContent().stream().map((topic) -> topicMapper.toTopicResponseDto(topic)).toList();

        PageResponse<TopicResponseDto> pageResponse =
                new PageResponse<>(topicResponseDtoList, topicPage.getNumber(), topicPage.getSize(),
                        topicPage.getTotalElements(), topicPage.getTotalPages());

        ApiResponse<PageResponse<TopicResponseDto>> apiResponse =
                new ApiResponse<>(true, "Topic list fetched successfully", pageResponse);

        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);

    }

    @GetMapping("/{topicId}")
    ResponseEntity<ApiResponse<TopicResponseDto>> getTopicById(@PathVariable("topicId") long topicId) {
        Topic topic = topicService.getTopicById(topicId);

        TopicResponseDto topicResponseDto = topicMapper.toTopicResponseDto(topic);

        ApiResponse<TopicResponseDto> apiResponse =
                new ApiResponse<>(true, "Topic found successfully", topicResponseDto);

        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);


    }

    @PutMapping("/{topicId}")
    ResponseEntity<ApiResponse<TopicResponseDto>> updateTopicById(@PathVariable Long topicId,
                                                                  @RequestBody @Valid TopicRequestDto topicRequestDto) {
        Topic topic = topicService.updateTopicById(topicId, topicRequestDto);
        topic.setTopicId(topicId);

        TopicResponseDto topicResponseDto = topicMapper.toTopicResponseDto(topic);

        ApiResponse<TopicResponseDto> apiResponse =
                new ApiResponse<>(true, "Topic updated successfully", topicResponseDto);

        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @DeleteMapping("/{topicId}")
    ResponseEntity<ApiResponse<Void>> deleteTopicById(@PathVariable Long topicId) {
        topicService.deleteByTopicId(topicId);

        ApiResponse<Void> apiResponse = new ApiResponse<>(true, "Topic deleted successfully", null);

        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @GetMapping("/{topicId}/problems")
    ResponseEntity<ApiResponse<PageResponse<ProblemResponseDto>>> getProblemsByTopic(@PathVariable Long topicId,
                                                                                     @RequestParam(defaultValue = "0")
                                                                                     int page,
                                                                                     @RequestParam(defaultValue = "10")
                                                                                     int size) {
        Page<Problem> problemPage = topicService.getProblemsByTopic(topicId, page, size);

        List<ProblemResponseDto> problemResponseDtoList =
                problemPage.getContent().stream().map((problem) -> problemMapper.toDto(problem)).toList();

        PageResponse<ProblemResponseDto> pageResponse =
                new PageResponse<>(problemResponseDtoList, problemPage.getNumber(), problemPage.getSize(),
                        problemPage.getTotalElements(), problemPage.getTotalPages());

        ApiResponse<PageResponse<ProblemResponseDto>> apiResponse =
                new ApiResponse<>(true, "Topic problems fetched successfully", pageResponse);

        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);

    }

}
