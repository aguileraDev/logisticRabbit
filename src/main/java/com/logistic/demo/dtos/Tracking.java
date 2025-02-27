package com.logistic.demo.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tracking {
    String agency;
    LocalDateTime arrivalTime;
    TrackingStatus status;
    String address;
    Package packet;

}
