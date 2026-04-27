package com.algotracker.AlgotrackerProject.mapper;

import com.algotracker.AlgotrackerProject.dto.TopicDto;
import com.algotracker.AlgotrackerProject.model.Topic;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TopicMapper {

    TopicDto toDto(Topic topic) {
        TopicDto topicDto = new TopicDto();

        topicDto.setId(topic.getTopicId());
        topicDto.setName(topic.getName());

        return topicDto;
    }
}
