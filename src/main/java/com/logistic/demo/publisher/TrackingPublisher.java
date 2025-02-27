package com.logistic.demo.publisher;

import com.logistic.demo.dtos.Tracking;
import com.logistic.demo.dtos.TrackingStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class TrackingPublisher {

    @Value("${rabbitmq.queue}")
    private String queue;

    private final Logger logger = LoggerFactory.getLogger(TrackingPublisher.class);

    private final RabbitTemplate rabbitTemplate;

    public TrackingPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public Tracking publish(Tracking tracking) {
        tracking.setArrivalTime(LocalDateTime.now().plusDays(1));
        tracking.setStatus(TrackingStatus.SENT);

        logger.info("Sending tracking", tracking);
        rabbitTemplate.convertAndSend(queue, tracking);

        return tracking;
    }
}
