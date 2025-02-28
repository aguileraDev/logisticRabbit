package com.logistic.demo.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tracking {
    String agency;
    LocalDateTime arrivalTime;
    TrackingStatus status;
    String address;
    List<Package> packages;

}
