package com.logistic.demo.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Package {
    private String sender;
    private String receptor;
    private Double weight;
    private Double distance;
    private Double price;
    private LocalDateTime sendDate;

}
