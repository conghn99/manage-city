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
public class UpsertEmployeeCertificationRequest {
    private Integer id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer employeeId;
    private Integer certificationId;
    private Integer cityId;
}
