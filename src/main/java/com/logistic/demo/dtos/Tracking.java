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
    private String agency;
    private LocalDateTime arrivalTime;
    private TrackingStatus status;
    private String address;
    private List<Package> packages;
    private Double total;


}
