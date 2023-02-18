package com.example.managecity.dto;

import com.example.managecity.entity.City;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CityDTO {
    private Integer id;
    private String name;
    private List<DistrictDTO> districtDto;

    public CityDTO(City entity) {
        this.setId(entity.getId());
        this.setName(entity.getName());
        districtDto.addAll(entity.getDistricts().stream().map(e -> new DistrictDTO(e)).collect(Collectors.toList()));
    }
}
