package com.logistic.demo.controllers;

import com.logistic.demo.dtos.Tracking;
import com.logistic.demo.publisher.TrackingPublisher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tracking")
public class TrackingController {

    private final TrackingPublisher trackingPublisher;

    public TrackingController(TrackingPublisher trackingPublisher) {
        this.trackingPublisher = trackingPublisher;
    }

    @PostMapping
    public Tracking sendTracking(Tracking tracking) {
        return trackingPublisher.publish(tracking);
    }
}
