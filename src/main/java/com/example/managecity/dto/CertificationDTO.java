package com.example.managecity.dto;

import com.example.managecity.entity.Certification;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CertificationDTO {
    private Integer id;
    private String name;
    private LocalDateTime startEffectiveDate;
    private LocalDateTime endEffectiveDate;
    private Integer cityId;

    public CertificationDTO(Certification entity) {
        this.setId(entity.getId());
        this.setName(entity.getName());
        this.setStartEffectiveDate(entity.getStartEffectiveDate());
        this.setEndEffectiveDate(entity.getEndEffectiveDate());
        this.setCityId(entity.getCity().getId());
    }
}
