package com.algotracker.AlgotrackerProject.mapper;

import com.algotracker.AlgotrackerProject.dto.TopicDto;
import com.algotracker.AlgotrackerProject.dto.TopicRequestDto;
import com.algotracker.AlgotrackerProject.dto.TopicResponseDto;
import com.algotracker.AlgotrackerProject.model.Topic;
import org.springframework.stereotype.Component;

@Component
public class TopicMapper {

    public TopicDto toDto(Topic topic) {
        TopicDto topicDto = new TopicDto();

        topicDto.setId(topic.getTopicId());
        topicDto.setName(topic.getName());

        return topicDto;
    }

    public Topic toEntityFromTopicRequest(TopicRequestDto topicRequestDto) {
        Topic topic = new Topic();

        topic.setName(topicRequestDto.getTopicName());
        topic.setDescription(topicRequestDto.getTopicDescription());
        return topic;
    }

    public TopicResponseDto toTopicResponseDto(Topic topic) {
        TopicResponseDto topicResponseDto = new TopicResponseDto();

        topicResponseDto.setTopicName(topic.getName());
        topicResponseDto.setTopicDescription(topic.getDescription());

        return topicResponseDto;
    }
}
