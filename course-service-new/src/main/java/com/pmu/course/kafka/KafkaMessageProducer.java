package com.pmu.course.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaMessageProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String topicName;

    @Autowired
    public KafkaMessageProducer(KafkaTemplate<String, String> kafkaTemplate,
                                @Value("${kafka.topic.name}") String topicName) {
        this.kafkaTemplate = kafkaTemplate;
        this.topicName = topicName;
    }

    public void publishMessage(String message) {
        kafkaTemplate.send(topicName, message);
    }
}

