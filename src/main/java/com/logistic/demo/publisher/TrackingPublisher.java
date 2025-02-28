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

    private final Double COST_BASE_PRICE = 5.0;
    private final Double FACTOR_DISTANCE = 0.10;
    private final Double FACTOR_WEIGHT = 0.25;

    private final Logger logger = LoggerFactory.getLogger(TrackingPublisher.class);

    private final RabbitTemplate rabbitTemplate;

    public TrackingPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public Tracking publish(Tracking tracking) {
        tracking.setArrivalTime(LocalDateTime.now().plusDays(1));
        tracking.setStatus(TrackingStatus.SENT);

        tracking.getPackages().forEach(p -> {
            p.setSendDate(LocalDateTime.now());
            p.setPrice(COST_BASE_PRICE +( p.getWeight() * FACTOR_WEIGHT )+ (p.getDistance() * FACTOR_DISTANCE));
        });

        tracking.setTotal(tracking.getPackages().stream().mapToDouble(p -> p.getPrice()).sum());

        boolean isOver500Km = tracking.getPackages().stream().anyMatch(p -> p.getDistance() > 500);

        if(isOver500Km){
            tracking.setTotal(tracking.getTotal() * 1.25);
            tracking.setArrivalTime(LocalDateTime.now().plusDays(2));
        }


        logger.info("Sending tracking", tracking);
        rabbitTemplate.convertAndSend(queue, tracking);

        return tracking;
    }
}
