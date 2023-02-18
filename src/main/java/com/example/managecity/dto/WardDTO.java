package com.example.managecity.dto;

import com.example.managecity.entity.Ward;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WardDTO {
    private Integer id;
    private String name;
    private Integer districtId;

    public WardDTO(Ward entity) {
        this.setId(entity.getId());
        this.setName(entity.getName());
        this.setDistrictId(entity.getDistrict().getId());
    }
}
