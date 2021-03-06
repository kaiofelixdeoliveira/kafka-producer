package com.br.thekingoftech.mensageria.components;

import org.springframework.beans.factory.annotation.Value;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
 
@Component
public class OrderProducer {

    @Value("${order.topic}")
    private String orderTopic;

    private final KafkaTemplate<String, String> kafkaTemplate;

    public OrderProducer(final KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(final @RequestBody String order) {
        final String mensageKey = UUID.randomUUID().toString();
        kafkaTemplate.send(orderTopic, mensageKey,  order);
    }

}
