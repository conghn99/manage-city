package com.example.managecity.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpsertCertificationRequest {
    private String name;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
