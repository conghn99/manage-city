package com.example.managecity.dto;

import com.example.managecity.entity.EmployeeCertification;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmployeeCertificationDTO {
    private Integer id;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer employeeId;
    private Integer cityId;
    private Integer certificationId;

    public EmployeeCertificationDTO(EmployeeCertification entity) {
        this.setId(entity.getId());
//        this.setStartDate(entity.getStartDate());
//        this.setEndDate(entity.getEndDate());
        this.setEmployeeId(entity.getEmployee().getId());
        this.setCityId(entity.getCity().getId());
        this.setCertificationId(entity.getCertification().getId());
    }
}
